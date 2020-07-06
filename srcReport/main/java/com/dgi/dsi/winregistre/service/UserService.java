package com.dgi.dsi.winregistre.service;//package com.dgi.dsi.winregistre.service.SauvService;
//
import com.dgi.dsi.winregistre.dao.AgentDao;
import com.dgi.dsi.winregistre.dao.UserRepository;
import com.dgi.dsi.winregistre.entites.Agent;
import com.dgi.dsi.winregistre.entites.AppRole;
import com.dgi.dsi.winregistre.entites.AppUser;
import com.dgi.dsi.winregistre.exception.CustomException;
import com.dgi.dsi.winregistre.sec.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

  @Autowired
  private AgentDao userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

//  @Autowired
//  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;


  @Autowired
  private AccountService accountService;

  public String createToken(String username, List<AppRole> roles) {

    Claims claims = Jwts.claims().setSubject(username);
    claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getRoleName())).filter(Objects::nonNull).collect(Collectors.toList()));

    Date now = new Date();
    Date validity = new Date(now.getTime() + SecurityConstants.EXPIRATION_TIME);

    return Jwts.builder()//
            .setClaims(claims)//
            .setIssuedAt(now)//
            .setExpiration(validity)//
            .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)//
            .compact();
  }

  public String signin(String username, String password) {
    try {

      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      username,
                      password
              )
      );

      List<AppRole> roles = new ArrayList<AppRole>();
      authentication.getAuthorities().forEach(r-> {
        roles.add(new AppRole(r.getAuthority()));
      });


      return "Bearer "+createToken(username, roles);
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String signup(Agent user) {
    if (!userRepository.existsByUsername(user.getPassword())) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);




      List<AppRole> roles = new ArrayList<AppRole>();
      user.getRoles().forEach(r-> {

        System.out.println("RRRRRRRRRRRRRRRRRRRRRR====>"+r.getRoleName());
        accountService.addRoleToUse(user.getUsername(), r.getRoleName());

      });

      return createToken(user.getUsername(), roles);
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }

  public Agent search(String username) {
      Agent user = userRepository.findByUsername(username);
    if (user == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return user;
  }

  public Agent whoami(HttpServletRequest req) {


    return userRepository.findByUsername(getUsername(resolveToken(req)));
  }

  public String refresh(String username) {
    List<AppRole> roles = new ArrayList<AppRole>();
    userRepository.findByUsername(username).getRoles().forEach(r-> {
      roles.add(new AppRole(r.getRoleName()));
    });

    return createToken(username, roles);
  }

  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody().getSubject();
  }

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }


}
