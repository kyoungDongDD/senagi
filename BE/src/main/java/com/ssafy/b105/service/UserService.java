package com.ssafy.b105.service;

import com.ssafy.b105.dto.UserDto;
import com.ssafy.b105.entity.Authority;
import com.ssafy.b105.entity.User;
import com.ssafy.b105.entity.UserType;
import com.ssafy.b105.exception.DuplicateException;
import com.ssafy.b105.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }
  //중복 체크
  @Transactional
  public UserDto signup(UserDto userDto) {
    // 중복 검증증

    //아이디 검증
    if (userRepository.findOneWithAuthoritiesByPrincipal(userDto.getName()).orElse(null) != null) {
      throw new DuplicateException("이미 가입되어 있는 유저입니다.");
    }

    //닉네임 검증
    if(userRepository.findOneByNickname(userDto.getNickname()).orElse(null) != null){
      throw new DuplicateException("이미 사용중인 닉네임 입니다.");
    }

    //권한 설정
    String role="";

    //보호소 롤 입력
    if(userDto.getType().equals("SHELTER")){
      role="ROLE_SUPPORTER";
    }
    //후원자 롤 입력
    else if(userDto.getType().equals("SUPPORTER")){
      role="ROLE_SUPPORTER";
    }

    Authority authority = Authority.builder()
      .authorityName(role)
      .build();

   User user = User.builder()
      .name(userDto.getName())
      .principal(userDto.getPrincipal())
      .credential(passwordEncoder.encode(userDto.getCredential()))
      .nickname(userDto.getNickname())
      .authorities(Collections.singleton(authority)) // 유저 권한 빌드
      .type(UserType.valueOf(userDto.getType()))
      .registDate(LocalDateTime.now())
      .build();

    return UserDto.from(userRepository.save(user));
  }
}
