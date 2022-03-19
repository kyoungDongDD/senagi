package com.ssafy.b105.service;

import com.ssafy.b105.dto.UserDto;
import com.ssafy.b105.entity.User;
import com.ssafy.b105.entity.UserAuthority;
import com.ssafy.b105.exception.DuplicateException;
import com.ssafy.b105.exception.ExpressionValidateException;
import com.ssafy.b105.repository.AuthorityRepository;
import com.ssafy.b105.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final AuthorityRepository authorityRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.authorityRepository = authorityRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public boolean duplicatePrincipalCheck(String principal) {
    //아이디 중복 검증
    if (userRepository.findOneWithUserAuthoritiesByPrincipal(principal).orElse(null) != null) {
      throw new DuplicateException("이미 가입되어 있는 아이디 입니다.");
    }
    return true;
  }

  @Transactional
  public boolean duplicateNameCheck(String name) {
    //닉네임 중복 검증
    if (userRepository.findOneByName(name).orElse(null) != null) {
      throw new DuplicateException("이미 사용중인 이름 입니다.");
    }
    return true;
  }

  @Transactional
  public UserDto supporterSignup(UserDto userDto) {
    //유효성 검증
    isValied(userDto);

   User user = User.builder()
      .name(userDto.getName())
      .principal(userDto.getPrincipal())
      .credential(passwordEncoder.encode(userDto.getCredential()))
      .build();

    //권한 설정
    UserAuthority userAuthority = UserAuthority.builder()
      .user(user)
      .authority(authorityRepository.getById(3L))
      .build();

    user.getUserAuthorities().add(userAuthority);

    return UserDto.from(userRepository.save(user));
  }

  @Transactional
  public UserDto shelterSignup(UserDto userDto) {
    //유효성 검증
    isValied(userDto);

    User user = User.builder()
      .name(userDto.getName())
      .principal(userDto.getPrincipal())
      .credential(passwordEncoder.encode(userDto.getCredential()))
      .phone(userDto.getPhone())
      .build();

    //권한 설정
    UserAuthority userAuthority = UserAuthority.builder()
      .user(user)
      .authority(authorityRepository.getById(2L))
      .build();

     user.getUserAuthorities().add(userAuthority);

    return UserDto.from(userRepository.save(user));
  }

  //유효성 검증
  private void isValied(UserDto userDto) {
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
    if (userRepository.findOneWithUserAuthoritiesByPrincipal(userDto.getPrincipal()).orElse(null) != null) {
      throw new DuplicateException("이미 가입되어 있는 아이디 입니다.");
    }
    //닉네임 중복 검증
    if(userRepository.findOneByName(userDto.getName()).orElse(null) != null){
      throw new DuplicateException("이미 사용중인 이름 입니다.");
    }
  }

}
