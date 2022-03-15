package com.ssafy.b105.controller;

import com.ssafy.b105.dto.UserDto;
import com.ssafy.b105.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
  private final UserService userService;
  public UserController(UserService userService) { this.userService = userService; }

  @PostMapping("/signUp")
  public ResponseEntity<UserDto> signup(@RequestBody UserDto userDto  ) {
    return ResponseEntity.ok(userService.signup(userDto));
  }
}