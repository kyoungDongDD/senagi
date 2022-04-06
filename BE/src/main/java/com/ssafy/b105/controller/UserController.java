package com.ssafy.b105.controller;

import com.ssafy.b105.dto.LoginRequestDto;
import com.ssafy.b105.dto.LoginResponseDto;
import com.ssafy.b105.auth.Jwt.domain.AuthenticationResult;
import com.ssafy.b105.auth.Jwt.domain.JwtAuthenticationToken;
import com.ssafy.b105.dto.SignupDto;
import com.ssafy.b105.entity.common.MemberType;
import com.ssafy.b105.entity.user.Authority;
import com.ssafy.b105.entity.user.User;
import com.ssafy.b105.entity.user.UserRole;
import com.ssafy.b105.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final UserService userService;


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
    @Valid @RequestBody SignupDto dto) throws ChangeSetPersister.NotFoundException {

    Set<Authority> authorities = new HashSet<>();
    UserRole role= UserRole.valueOf("SUPPORTER");

    Authority authority= Authority.builder()
      .authority(role)
      .build();
    authorities.add(authority);

    User user =User.builder()
      .principal(dto.getPrincipal())
      .credential(dto.getCredential())
      .name(dto.getName())
      .authorities(authorities)
      .build();

    authority.setUser(user);
    userService.saveOrUpdateUser(user, MemberType.Supporter);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  @PostMapping("/signup/shelter")
  public ResponseEntity<?> insertShelter(
    @Valid @RequestBody SignupDto dto) throws ChangeSetPersister.NotFoundException {

    Set<Authority> authorities = new HashSet<>();
    UserRole role= UserRole.valueOf("SHELTER");

    Authority authority= Authority.builder()
      .authority(role)
      .build();
    authorities.add(authority);

    User user =User.builder()
      .principal(dto.getPrincipal())
      .credential(dto.getCredential())
      .name(dto.getName())
      .phone(dto.getPhone())
      .authorities(authorities)
      .build();

    authority.setUser(user);
    userService.saveOrUpdateUser(user,MemberType.Shelter);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }
  @PostMapping("/login/shelter")
  public ResponseEntity<?> loginShelter(@RequestBody LoginRequestDto loginDto){
    log.info("보호소 로그인 요청을 받았습니다.");
    JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(loginDto.getPrincipal(), loginDto.getCredential());

    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    log.debug("보호소 권한 , authentication : " ,authentication.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(authentication);
    log.info("보호소 Authentication set 완료");

  return ResponseEntity.ok(new LoginResponseDto((AuthenticationResult) authentication.getDetails()));
  }

  @PostMapping("/login/supporter")
  public ResponseEntity<?> loginSupporter(@RequestBody LoginRequestDto loginDto){
    log.info("서포터 로그인 요청을 받았습니다.");
    JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(loginDto.getPrincipal(), loginDto.getCredential());

    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    log.debug("후원자 권한 , authentication : " ,authentication.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(authentication);
    log.info("후원자 Authentication set 완료");
    return ResponseEntity.ok(new LoginResponseDto((AuthenticationResult) authentication.getDetails()));
  }

}
