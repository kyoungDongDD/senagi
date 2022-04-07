package com.ssafy.b105.auth.Jwt.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt.token")
public class JwtTokenConfig {

  private String header;
  private String issuer;
  private String secret;
  private long expirySeconds;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
