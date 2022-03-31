package com.ssafy.b105.auth.Jwt.domain;

import com.ssafy.b105.entity.user.User;
import lombok.Getter;

@Getter
public class AuthenticationResult {

  private final String apiToken;

  private final User user;

  private AuthenticationResult(String apiToken, User user) {
    this.apiToken = apiToken;
    this.user = user;
  }

  public static AuthenticationResult from(String apiToken, User user) {
    isNotNull(apiToken);
    isNotNull(user);
    return new AuthenticationResult(apiToken, user);
  }

  private static void isNotNull(Object obj) {
    if(obj == null)
      throw new IllegalArgumentException("AuthenticationResult Error");
  }
}
