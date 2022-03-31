package com.ssafy.b105.auth.Jwt.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@RequiredArgsConstructor
public class AuthenticationRequest {

  private final String principal;

  private final String credentials;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
