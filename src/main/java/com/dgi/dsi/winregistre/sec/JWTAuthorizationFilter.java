package com.dgi.dsi.winregistre.sec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", 
				"Origin, Accept, X-Requested-With, Content-Type, "
				+ "Access-Control-Request-Method, "
				+ "Access-Control-Request-Headers,"
				+ "Authorization");
		response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, "
				+ "Access-Control-Allow-Credentials, Authorization");		
	
		
		String jwt=request.getHeader(SecurityConstants.HEADER_STRING);

		System.out.println("JWTAuthorizationFilter JWT ------------>"+jwt);

		if(request.getMethod().equals("OPTIONS")) {
		response.setStatus(HttpServletResponse.SC_OK);
	    }
		else
		{
			if(jwt==null || !jwt.startsWith(SecurityConstants.TKEN_PREFIX)) {
				filterChain.doFilter(request, response); 
				return;
			}
			
			Claims claims=Jwts.parser()
					.setSigningKey(SecurityConstants.SECRET)
					.parseClaimsJws(jwt.replace(SecurityConstants.TKEN_PREFIX, ""))
					.getBody();
			
			String username=claims.getSubject();
			System.out.println("JWTAuthorizationFilter claims------------>"+claims);
			Integer iat =(Integer) claims.get("iat");
			System.out.println("iat--------->"+iat);

			ArrayList<Map<String,String>> roles;
			if(iat == null){
				System.out.println("OK");
				roles=(ArrayList<Map<String,String>>)claims.get("roles");
			}else{
				System.out.println("Non Ok");
				roles=(ArrayList<Map<String,String>>)claims.get("auth");
			}

//			ArrayList<Map<String,String>> roles=(ArrayList<Map<String,String>>)claims.get("roles");//auth; roles
			System.out.println(claims);
			
			Collection<GrantedAuthority>authorities=new ArrayList<>();
			roles.forEach(r->{
				authorities.add(new SimpleGrantedAuthority(r.get("authority")));
			});		
			
			UsernamePasswordAuthenticationToken authenticateduser=
					new UsernamePasswordAuthenticationToken(username,null,authorities);
			
			SecurityContextHolder.getContext().setAuthentication(authenticateduser);
			
			filterChain.doFilter(request, response);
		}
				
		
		
	}

}
