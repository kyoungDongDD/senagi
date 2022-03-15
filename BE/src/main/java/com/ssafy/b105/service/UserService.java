package com.ssafy.b105.service;

import com.ssafy.b105.dto.UserDto;
import com.ssafy.b105.entity.User;
import com.ssafy.b105.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public UserDto signup(UserDto userDto) {

    User user = User.builder()
      .name(userDto.getName())
      .principal(userDto.getPrincipal())
      .credential(passwordEncoder.encode(userDto.getCredential()))
      .nickname(userDto.getNickname())
      .type(userDto.getType())
      .registDate(LocalDateTime.now())
      .build();

    return UserDto.from(userRepository.save(user));
  }
}
