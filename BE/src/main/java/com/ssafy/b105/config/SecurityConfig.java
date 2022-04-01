package com.ssafy.b105.config;

import com.ssafy.b105.auth.oauth.PrincipalOauth2UserService;
import com.ssafy.b105.auth.Jwt.domain.Jwt;
import com.ssafy.b105.auth.Jwt.JwtFilter;
import com.ssafy.b105.auth.Jwt.domain.JwtTokenConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsUtils;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


  private final Jwt jwt;
  private final JwtTokenConfig jwtTokenConfig;
//  private final PrincipalOauth2UserService principalOauth2UserService;


  @Bean
  public JwtFilter jwtFilter() {
    return new JwtFilter(jwt, jwtTokenConfig.getHeader());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web
      .ignoring()
      .antMatchers(
        "/h2-console/**",
        "/favicon/ico",
        "/static/**"
      );
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.httpBasic().disable()
      .csrf()
      .disable();

    http
      .headers()
      .frameOptions()
      .disable();

    // 서버에서 인증은 JWT로 인증하기 때문에 Session의 생성을 막습니다.
    http
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http
      .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

    http
      .authorizeRequests()
      .requestMatchers(request -> CorsUtils.isPreFlightRequest(request)).permitAll()
      .mvcMatchers("/api/auth/**")
      .permitAll()

      .and()

      .authorizeRequests()
      .mvcMatchers("/api/campaign/**")
      .permitAll()

      .and()

      .formLogin().disable()
      .oauth2Login()
      .userInfoEndpoint();
//      .userService(principalOauth2UserService);
  }
}