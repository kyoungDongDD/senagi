package com.ssafy.b105.dto;

import com.ssafy.b105.auth.Jwt.domain.AuthenticationResult;
import com.ssafy.b105.entity.user.User;
import lombok.Getter;

@Getter
public class LoginResponseDto {
  private final String jwtToken;

  public LoginResponseDto(AuthenticationResult authenticationResult) {
    this.jwtToken = authenticationResult.getApiToken();
  }

  public LoginResponseDto(String token, User user) {
    this.jwtToken = token;
  }
}
