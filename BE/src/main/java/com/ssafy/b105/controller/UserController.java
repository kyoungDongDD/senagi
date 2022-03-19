package com.ssafy.b105.controller;

import com.ssafy.b105.dto.UserDto;
import com.ssafy.b105.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
  private final UserService userService;
  public UserController(UserService userService) { this.userService = userService; }

  @GetMapping("/duplicate/principal")
  public ResponseEntity<Boolean> duplicatePrincipalCheck(@RequestParam String principal){
    return ResponseEntity.ok(userService.duplicatePrincipalCheck(principal));
  }
  @GetMapping("/duplicate/name")
  public ResponseEntity<Boolean> duplicateNameCheck(@RequestParam String name){
    return ResponseEntity.ok(userService.duplicateNameCheck(name));
  }
  @PostMapping("/signup/supporter")
  public ResponseEntity<UserDto> supporterSignup(@RequestBody UserDto userDto  ) {
    return ResponseEntity.ok(userService.supporterSignup(userDto));
  }
  @PostMapping("/signup/shelter")
  public ResponseEntity<UserDto> shelterSignup(@RequestBody UserDto userDto  ) {
    return ResponseEntity.ok(userService.shelterSignup(userDto));
  }
}