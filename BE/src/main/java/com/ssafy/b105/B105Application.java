package com.ssafy.b105;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class B105Application {

  public static void main(String[] args) {
    SpringApplication.run(B105Application.class, args);
  }

}
