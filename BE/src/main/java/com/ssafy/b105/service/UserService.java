package com.ssafy.b105.service;

import com.ssafy.b105.dto.UserDto;
import com.ssafy.b105.entity.Authority;
import com.ssafy.b105.entity.User;
import com.ssafy.b105.entity.UserAuthority;
import com.ssafy.b105.entity.UserType;
import com.ssafy.b105.exception.DuplicateException;
import com.ssafy.b105.exception.ExpressionValidateException;
import com.ssafy.b105.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.regex.Pattern;

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

    //아이디 정규식 검증
    if (userDto.getPrincipal().isBlank()) throw new NullPointerException("아이디를 입력하십시오.");
    else if(!Pattern.matches("([\\w-.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$",userDto.getPrincipal().trim())){
      throw new ExpressionValidateException("아이디 형식이 올바르지 않습니다.");
    }

    //비밀번호 정규식 검증
    if (userDto.getCredential().isBlank()) throw new NullPointerException("비밀 번호를 입력하십시오.");
    else if(!Pattern.matches("^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$",userDto.getCredential().trim())){
      throw new ExpressionValidateException("비밀번호 형식이 올바르지 않습니다.");
    }

    //닉네임 정규식 검증
    if (userDto.getName().isBlank()) throw new NullPointerException("닉네임을 입력하십시오.");
    else if(!Pattern.matches("^[가-힣a-zA-Z]+$",userDto.getName().trim())){
      throw new ExpressionValidateException("닉네임 형식이 올바르지 않습니다.");
    }

    //아이디 중복 검증
    if (userRepository.findOneWithAuthoritiesByPrincipal(userDto.getPrincipal()).orElse(null) != null) {
      throw new DuplicateException("이미 가입되어 있는 아이디 입니다.");
    }

    //닉네임 중복 검증
    if(userRepository.findOneByName(userDto.getName()).orElse(null) != null){
      throw new DuplicateException("이미 사용중인 이름 입니다.");
    }

    //권한 설정
    String role="";

    //보호소 롤 입력
    if(userDto.getType().equals("SHELTER")){
      role="ROLE_SHELTER";
    }
    //후원자 롤 입력
    else if(userDto.getType().equals("SUPPORTER")){
      role="ROLE_SUPPORTER";
    }

    Authority authority = Authority.builder()
      .authorityName(role)
      .build();

    UserAuthority userAuthority = UserAuthority.builder()
      .authority(authority)
      .build();

   User user = User.builder()
      .name(userDto.getName())
      .principal(userDto.getPrincipal())
      .credential(passwordEncoder.encode(userDto.getCredential()))
      .authorities(Collections.singleton(userAuthority)) // 유저 권한 빌드
      .type(UserType.valueOf(userDto.getType()))
      .phone(userDto.getPhone())
      .build();

    return UserDto.from(userRepository.save(user));
  }
}
