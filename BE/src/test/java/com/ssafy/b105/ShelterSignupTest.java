package com.ssafy.b105;

import com.ssafy.b105.dto.UserDto;
import com.ssafy.b105.entity.User;
import com.ssafy.b105.exception.DuplicateException;
import com.ssafy.b105.exception.ExpressionValidateException;
import com.ssafy.b105.repository.UserRepository;
import com.ssafy.b105.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ShelterSignupTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  @Test
  @DisplayName("단순 user DB저장 테스트")
  void shelterSaveTest() {

    int k=userRepository.findAll().size();

    User user = User.builder()
      .principal("rlarudehd321@naver.com")
      .credential("Asd123!!")
      .name("김디디다")
      .phone("010-2321-1231")
      .build();

    userRepository.save(user);
    //기본 admin유저1개 존재
    List<User> users= userRepository.findAll();
    // 저장이 되었다면 k+1개 안되었다면 k개
    assertThat(users.size()).isEqualTo(k+1);
  }

  @Test
  @DisplayName("유저 일반회원가입 테스트")
  void shelterSignupSaveTest() {
    //문제없는 userDTO
    int k=userRepository.findAll().size();
    
    UserDto userDto = UserDto.builder()
      .principal("rlarudehd32@naver.com")
      .credential("Asd123!!")
      .name("김디디")       
      .phone("010-2321-1231")
      .build();

    userService.shelterSignup(userDto);
    //기본 admin유저1개 존재
    List<User> users= userRepository.findAll();
    // 저장이 되었다면 k+1개 안되었다면 k개
    assertThat(users.size()).isEqualTo(k+1);

  }

  @Test
  @DisplayName("유저 중복 id exception 테스트")
  void shelterSignupIdDuplicateTest() {
    // 중복 id userDto
    int k=userRepository.findAll().size();

    UserDto userDto = UserDto.builder()
      .principal("admin@b105.io")// 저장되어있는 admin과 같은 principal
      .credential("Asd123!!")
      .name("김디디")
      .phone("010-2321-1231")
      .build();
    try {
      userService.shelterSignup(userDto);
    }
    catch (DuplicateException msg){
      //메시지 테스트
      System.out.println("msg = " + msg);
      assertThat(msg.getMessage()).isEqualTo("이미 가입되어 있는 아이디 입니다.");
    }
    //기본 admin유저1개 존재
    List<User> users= userRepository.findAll();
    // 저장이 되었다면 k+1개 안되었다면 k개
    assertThat(users.size()).isEqualTo(k);
  }

  @Test
  @DisplayName("유저 중복 name exception 테스트")
  void shelterSignupNameDuplicateTest() {
    // 중복 name userDto
    int k=userRepository.findAll().size();

    UserDto userDto = UserDto.builder()
      .principal("rlarudehd132@naver.com")
      .credential("Asd123!!")
      .name("동동스") // 저장되어있는 admin과 같은 name
      .phone("010-2321-1231")
      .build();
    try {
      userService.shelterSignup(userDto);
    }
    catch (DuplicateException msg){
      //메시지 테스트
      assertThat(msg.getMessage()).isEqualTo("이미 사용중인 이름 입니다.");
    }
    //기본 admin유저1개 존재
    List<User> users= userRepository.findAll();
    // 저장이 되었다면 k+1개 안되었다면 k개
    assertThat(users.size()).isEqualTo(k);
  }

  @Test
  @DisplayName("빈 principal exception 테스트")
  void shelterSignupIdIsBlankTest() {
    // 빈 principal userDto
    int k=userRepository.findAll().size();

    UserDto userDto = UserDto.builder()
      .principal("")  //빈 principal
      .credential("Asd123!!")
      .name("동동스")
      .phone("010-2321-1231")
      .build();
    try {
      userService.shelterSignup(userDto);
    }
    catch (NullPointerException msg){
      //메시지 테스트
      assertThat(msg.getMessage()).isEqualTo("아이디를 입력하십시오.");
    }
    //기본 admin유저1개 존재
    List<User> users= userRepository.findAll();
    // 저장이 되었다면 k+1개 안되었다면 k개
    assertThat(users.size()).isEqualTo(k);
  }

  @Test
  @DisplayName("빈 principal exception 테스트")
  void shelterSignupCredentialIsBlankTest() {
    // 빈 credential userDto
    int k=userRepository.findAll().size();

    UserDto userDto = UserDto.builder()
      .principal("rlarudehd32@naver.com")
      .credential("") //빈 credential
      .name("동동스")
      .phone("010-2321-1231")
      .build();
    try {
      userService.shelterSignup(userDto);
    }
    catch (NullPointerException msg){
      //메시지 테스트
      assertThat(msg.getMessage()).isEqualTo("비밀 번호를 입력하십시오.");
    }
    //기본 admin유저1개 존재
    List<User> users= userRepository.findAll();
    // 저장이 되었다면 k+1개 안되었다면 k개
    assertThat(users.size()).isEqualTo(k);
  }

  @Test
  @DisplayName("빈 name exception 테스트")
  void shelterSignupNameIsBlankTest() {
    // 빈 name userDto
    int k=userRepository.findAll().size();

    UserDto userDto = UserDto.builder()
      .principal("rlarudehd32@naver.com")
      .credential("Asd123!!")
      .name("") //빈 name
      .phone("010-2321-1231")
      .build();
    try {
      userService.shelterSignup(userDto);
    }
    catch (NullPointerException msg){
      //메시지 테스트
      assertThat(msg.getMessage()).isEqualTo("닉네임을 입력하십시오.");
    }
    //기본 admin유저1개 존재
    List<User> users= userRepository.findAll();
    // 저장이 되었다면 k+1개 안되었다면 k개
    assertThat(users.size()).isEqualTo(k);
  }

  @Test
  @DisplayName("principal 정규식 exception 테스트")
  void shelterSignupPrincipalExpressionValidateTest() {
    // 정규식에 맞지 않는 principal userDto
    int k=userRepository.findAll().size();

    UserDto userDto = UserDto.builder()
      .principal("rlarudehd") // 형식에 맞지 않는 principal
      .credential("Asd123!!")
      .name("동동스")
      .phone("010-2321-1231")
      .build();
    try {
      userService.shelterSignup(userDto);
    }
    catch (ExpressionValidateException msg){
      //메시지 테스트
      assertThat(msg.getMessage()).isEqualTo("아이디 형식이 올바르지 않습니다.");
    }
    //기본 admin유저1개 존재
    List<User> users= userRepository.findAll();
    // 저장이 되었다면 k+1개 안되었다면 k개
    assertThat(users.size()).isEqualTo(k);
  }

  @Test
  @DisplayName("credential 정규식 exception 테스트")
  void shelterSignupCredentialExpressionValidateTest() {
    // 정규식에 맞지 않는 credential userDto
    int k=userRepository.findAll().size();

    UserDto userDto = UserDto.builder()
      .principal("rlarudehd32@naver.com")
      .credential("Asd") // 형식에 맞지 않는 credential
      .name("동동스")
      .phone("010-2321-1231")
      .build();
    try {
      userService.shelterSignup(userDto);
    }
    catch (ExpressionValidateException msg){
      //메시지 테스트
      assertThat(msg.getMessage()).isEqualTo("비밀번호 형식이 올바르지 않습니다.");
    }
    //기본 admin유저1개 존재
    List<User> users= userRepository.findAll();
    // 저장이 되었다면 k+1개 안되었다면 k개
    assertThat(users.size()).isEqualTo(k);
  }

  @Test
  @DisplayName("credential 정규식 exception 테스트")
  void shelterSignupNameExpressionValidateTest() {
    // 정규식에 맞지 않는 credential userDto
    int k=userRepository.findAll().size();

    UserDto userDto = UserDto.builder()
      .principal("rlarudehd32@naver.com")
      .credential("Asd123!!") // 형식에 맞지 않는 credential
      .name("동동스@@!")
      .phone("010-2321-1231")
      .build();
    try {
      userService.shelterSignup(userDto);
    }
    catch (ExpressionValidateException msg){
      //메시지 테스트
      assertThat(msg.getMessage()).isEqualTo("닉네임 형식이 올바르지 않습니다.");
    }
    //기본 admin유저1개 존재
    List<User> users= userRepository.findAll();
    // 저장이 되었다면 k+1개 안되었다면 k개
    assertThat(users.size()).isEqualTo(k);
  }
}
