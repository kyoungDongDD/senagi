package com.ssafy.b105;

import com.ssafy.b105.exception.DuplicateException;
import com.ssafy.b105.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class DuplicationTest {
  @Autowired
  private UserService userService;

  @Test
  @DisplayName("아이디 중복 확인 테스트(중복)")
  void duplicatePrincipalCheckTest(){
    String principal="admin@b105.io"; //기본 들어가있는 admin id
    String state="아이디 사용 가능";
    try {
      userService.duplicatePrincipalCheck(principal); //검증
    }
    catch (DuplicateException msg){ //중복 에러 발생
      state="아이디 사용 불가";
      System.out.println("msg = " + msg.getMessage());
      assertThat(msg.getMessage()).isEqualTo("이미 가입되어 있는 아이디 입니다.");
    }
    assertThat(state).isEqualTo("아이디 사용 불가");
  }
  @Test
  @DisplayName("아이디 중복 확인 테스트(비중복)")
  void nonDuplicatePrincipalCheckTest(){
    String principal="rlarudehd32@naver.com";
      assertThat(userService.duplicatePrincipalCheck(principal)).isEqualTo(true);
  }

  @Test
  @DisplayName("닉네임 중복 확인 테스트(중복)")
  void duplicateNameCheckTest(){
    String name="동동스";
    String state="닉네임 사용 가능";
    try {
      userService.duplicateNameCheck(name);
    }
    catch (DuplicateException msg){
      state="닉네임 사용 불가";
      System.out.println("msg = " + msg.getMessage());
      assertThat(msg.getMessage()).isEqualTo("이미 사용중인 이름 입니다.");
    }
    assertThat(state).isEqualTo("닉네임 사용 불가");
  }
  @Test
  @DisplayName("닉네입 중복 확인 테스트(비중복)")
  void nonDuplicateNameCheckTest(){
    String name="산와머니";
    assertThat(userService.duplicatePrincipalCheck(name)).isEqualTo(true);
  }
}
