package com.dgi.dsi.winregistre.api;

import com.dgi.dsi.winregistre.dao.AgentDao;
import com.dgi.dsi.winregistre.dao.RoleDao;


import com.dgi.dsi.winregistre.entites.Agent;
import com.dgi.dsi.winregistre.entites.AppUser;

import com.dgi.dsi.winregistre.payload.LoginRequest;
import com.dgi.dsi.winregistre.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthAgentApi {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AgentDao userRepository;

    @Autowired
    RoleDao roleRepository;

    //    @Autowired
//    JwtTokenProvider tokenProvider;
//
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/signin")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 404, message = "Not Found"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){

//                                   @ApiParam("Username") @RequestParam String username, //
//                                   @ApiParam("Password") @RequestParam String password)

//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        username,
//                        password
//                )
//        );
//
//
//        System.out.println("------authentication----------"+authentication.getCredentials()+" ***********  "+authentication.getAuthorities()+"---"+authentication.getPrincipal()+"------authentication----------");
//
////        SecurityContextHolder.getContext().setAuthentication(authentication);
//        List<AppRole> roles = new ArrayList<AppRole>();
//        authentication.getAuthorities().forEach(r-> {
//            roles.add(new AppRole(r.getAuthority()));
//        });
//
//        String jwt = userService.createToken(username, roles);
//        jwt = "Bearer "+jwt;
//        System.out.println("--------------jwt------------"+jwt+"--------------jwt------------");
//        return ResponseEntity.ok(jwt);
        return ResponseEntity.ok(userService.signin(loginRequest.getUsername(), loginRequest.getPassword()));
    }



    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String signup(@ApiParam("Signup Agent") @RequestBody Agent user) {
        return userService.signup(modelMapper.map(user, Agent.class));
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "${UserController.delete}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String delete(@ApiParam("Username") @PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UserController.search}", response = AppUser.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public AppUser search(@ApiParam("Username") @PathVariable String username) {
        return modelMapper.map(userService.search(username), AppUser.class);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @ApiOperation(value = "${UserController.me}", response = AppUser.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public Agent whoami(HttpServletRequest req) {

        return modelMapper.map(userService.whoami(req), Agent.class);
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String refresh(HttpServletRequest req) {

        return userService.refresh(req.getRemoteUser());
    }



}
