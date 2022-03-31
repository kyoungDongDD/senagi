package com.ssafy.b105.auth.Jwt.domain;

import lombok.Getter;

@Getter
public class JwtAuthentication {

  private final Long id;
  private final String principal;
  private final String name;

  private JwtAuthentication(Long userId,String principal, String name) {
    this.id = userId;
    this.principal= principal;
    this.name = name;
  }

  public static JwtAuthentication of(final Long userId,final String principal, final String name) {
    isNotNull(principal);
    isNotNull(userId);


    return new JwtAuthentication(userId,principal, name);
  }

  // Validation
  private static void isNotNull(Object obj) {
    if(obj == null)
      throw new IllegalArgumentException("parameter must be provided");
  }
}