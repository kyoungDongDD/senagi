package com.ssafy.b105.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfigure {

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
