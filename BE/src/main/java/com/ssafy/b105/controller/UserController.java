package com.ssafy.b105.controller;

import com.ssafy.b105.dto.UserFormDTO;
import com.ssafy.b105.entity.User;
import com.ssafy.b105.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

  private final UserServiceImpl userService;

  @GetMapping("/duplicate/principal")
  public ResponseEntity<Boolean> duplicatePrincipalCheck(@RequestParam String principal){
    return ResponseEntity.ok(userService.duplicatePrincipalCheck(principal));
  }
  @GetMapping("/duplicate/name")
  public ResponseEntity<Boolean> duplicateNameCheck(@RequestParam String name){
    return ResponseEntity.ok(userService.duplicateNameCheck(name));
  }

  @PostMapping("/signup/supporter")
  public ResponseEntity<?> insertSupporter(
    @Valid @RequestBody UserFormDTO dto,
    BindingResult bindingResult  ) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    User accountDB = userService.saveOrUpdateUser(dto.toEntity());

    return new ResponseEntity<>(accountDB, HttpStatus.CREATED);
  }

  @PostMapping("/signup/shelter")
  public ResponseEntity<?> insertShelter(
    @Valid @RequestBody UserFormDTO dto,
    BindingResult bindingResult  ) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    User accountDB = userService.saveOrUpdateUser(dto.toEntity());

    return new ResponseEntity<>(accountDB, HttpStatus.CREATED);
  }
}