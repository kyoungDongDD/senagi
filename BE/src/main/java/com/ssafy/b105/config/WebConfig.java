package com.ssafy.b105.config;

import com.ssafy.b105.constant.EnumMapper;
import com.ssafy.b105.entity.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Order(1)
@Configuration
public class WebConfig {
  @Bean
  public EnumMapper enumMapper() {
    EnumMapper enumMapper = new EnumMapper();
    enumMapper.put("UserRole", UserRole.class);

    return enumMapper;
  }
  // Spring Security 5 권장하는 인코더
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
