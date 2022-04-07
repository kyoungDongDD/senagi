package com.ssafy.b105.config;

import com.ssafy.b105.auth.Jwt.domain.Jwt;
import com.ssafy.b105.auth.Jwt.domain.JwtTokenConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ServiceConfigure {


  @Bean
  public Jwt jwt(JwtTokenConfig jwtTokenConfig) {
    return new Jwt(jwtTokenConfig);
  }



}
