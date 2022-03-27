package com.ssafy.b105.config;

import com.ssafy.b105.config.oauth.PrincipalOauth2UserService;
import com.ssafy.b105.security.common.FilterSkipMatcher;
import com.ssafy.b105.security.filter.FormLoginFilter;
import com.ssafy.b105.security.filter.JwtAuthenticationFilter;
import com.ssafy.b105.security.handler.FormLoginAuthenticationFailureHandler;
import com.ssafy.b105.security.handler.FormLoginAuthenticationSuccessHandler;
import com.ssafy.b105.security.jwt.HeaderTokenExtractor;
import com.ssafy.b105.security.provider.FormLoginAuthenticationProvider;
import com.ssafy.b105.security.provider.JWTAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final FormLoginAuthenticationSuccessHandler formLoginAuthenticationSuccessHandler;
    private final FormLoginAuthenticationFailureHandler formLoginAuthenticationFailureHandler;
    private final PrincipalOauth2UserService principalOauth2UserService;

    private final FormLoginAuthenticationProvider provider;
    private final JWTAuthenticationProvider jwtProvider;
    private final HeaderTokenExtractor headerTokenExtractor;

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    // 1.
    protected FormLoginFilter formLoginFilter() throws Exception {
        FormLoginFilter filter = new FormLoginFilter(
                new AntPathRequestMatcher("/api/auth/login/**", HttpMethod.POST.name()),
                formLoginAuthenticationSuccessHandler,
                formLoginAuthenticationFailureHandler
        );
        filter.setAuthenticationManager(super.authenticationManagerBean());

        return filter;
    }

    // 2.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(this.provider)
                .authenticationProvider(this.jwtProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
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

        /*
         * 1.
         * UsernamePasswordAuthenticationFilter 이전에 FormLoginFilter, JwtFilter 를 등록합니다.
         * FormLoginFilter : 로그인 인증을 실시합니다.
         * JwtFilter       : 서버에 접근시 JWT 확인 후 인증을 실시합니다.
         */
        http
                .addFilterBefore(
                        formLoginFilter(),
                        UsernamePasswordAuthenticationFilter.class
                )
                .addFilterBefore(
                        jwtFilter(),
                        UsernamePasswordAuthenticationFilter.class
                );

        // api/auth 에는 모두 허용
        http
                .authorizeRequests()
                .mvcMatchers(
                        HttpMethod.GET,
                        "/api/auth/**"
                )
                .permitAll()
          //TODO:롤 입력이 안된건지 or hasRole이 안된건지 확인 필요..
                .mvcMatchers(
                        HttpMethod.POST,
                        "/api/auth/signup/shelter"
                )
                .hasRole("ADMIN")
          .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(principalOauth2UserService);
    }

    private JwtAuthenticationFilter jwtFilter() throws Exception {
        List<AntPathRequestMatcher> skipPath = new ArrayList<>();

        // Static 정보 접근 허용
        skipPath.add(new AntPathRequestMatcher("/error", HttpMethod.GET.name()));
        skipPath.add(new AntPathRequestMatcher("/favicon.ico", HttpMethod.GET.name()));
        skipPath.add(new AntPathRequestMatcher("/static", HttpMethod.GET.name()));
        skipPath.add(new AntPathRequestMatcher("/static/**", HttpMethod.GET.name()));

        skipPath.add(new AntPathRequestMatcher("/api/auth/**", HttpMethod.POST.name()));
        skipPath.add(new AntPathRequestMatcher("/api/auth/login/**", HttpMethod.POST.name()));
        skipPath.add(new AntPathRequestMatcher("/api/auth/duplicate/**", HttpMethod.GET.name()));


        FilterSkipMatcher matcher = new FilterSkipMatcher(
                skipPath,
                "/**"
        );

        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(
                matcher,
                headerTokenExtractor
        );
        filter.setAuthenticationManager(super.authenticationManagerBean());

        return filter;
    }
}