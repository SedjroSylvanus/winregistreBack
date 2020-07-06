//package com.dgi.dsi.winregistre.api;
//
//import com.dgi.dsi.winregistre.service.UserService;
//import io.swagger.annotations.*;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import springfox.documentation.swagger2.mappers.ModelMapper;
//
//import javax.servlet.http.HttpServletRequest;
//
//@RestController
//@RequestMapping("/users")
//@Api(tags = "users")
//public class UserController {
//
//  @Autowired
//  private UserService userService;
//
//  @Autowired
//  private ModelMapper modelMapper;
//
//  @PostMapping("/signin")
//  @ApiOperation(value = "${UserController.signin}")
//  @ApiResponses(value = {//
//      @ApiResponse(code = 400, message = "Something went wrong"), //
//      @ApiResponse(code = 422, message = "Invalid username/password supplied")})
//  public String login(//
//                      @ApiParam("Username") @RequestParam String username, //
//                      @ApiParam("Password") @RequestParam String password) {
//    return userService.signin(username, password);
//  }
//
//  @PostMapping("/signup")
//  @ApiOperation(value = "${UserController.signup}")
//  @ApiResponses(value = {//
//      @ApiResponse(code = 400, message = "Something went wrong"), //
//      @ApiResponse(code = 403, message = "Access denied"), //
//      @ApiResponse(code = 422, message = "Username is already in use"), //
//      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
//  public String signup(@ApiParam("Signup User") @RequestBody UserDataDTOOld user) {
//    return userService.signup(modelMapper.map(user, User.class));
//  }
//
//  @DeleteMapping(value = "/{username}")
//  @PreAuthorize("hasRole('ROLE_ADMIN')")
//  @ApiOperation(value = "${UserController.delete}")
//  @ApiResponses(value = {//
//      @ApiResponse(code = 400, message = "Something went wrong"), //
//      @ApiResponse(code = 403, message = "Access denied"), //
//      @ApiResponse(code = 404, message = "The user doesn't exist"), //
//      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
//  public String delete(@ApiParam("Username") @PathVariable String username) {
//    userService.delete(username);
//    return username;
//  }
//
//  @GetMapping(value = "/{username}")
//  @PreAuthorize("hasRole('ROLE_ADMIN')")
//  @ApiOperation(value = "${UserController.search}", response = UserResponseDTOOld.class)
//  @ApiResponses(value = {//
//      @ApiResponse(code = 400, message = "Something went wrong"), //
//      @ApiResponse(code = 403, message = "Access denied"), //
//      @ApiResponse(code = 404, message = "The user doesn't exist"), //
//      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
//  public UserResponseDTOOld search(@ApiParam("Username") @PathVariable String username) {
//    return modelMapper.map(userService.search(username), UserResponseDTOOld.class);
//  }
//
//  @GetMapping(value = "/me")
//  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
//  @ApiOperation(value = "${UserController.me}", response = UserResponseDTOOld.class)
//  @ApiResponses(value = {//
//      @ApiResponse(code = 400, message = "Something went wrong"), //
//      @ApiResponse(code = 403, message = "Access denied"), //
//      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
//  public UserResponseDTOOld whoami(HttpServletRequest req) {
//    return modelMapper.map(userService.whoami(req), UserResponseDTOOld.class);
//  }
//
//  @GetMapping("/refresh")
//  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
//  public String refresh(HttpServletRequest req) {
//    return userService.refresh(req.getRemoteUser());
//  }
//
//}
