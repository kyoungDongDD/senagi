package com.ssafy.b105.auth.Jwt;

import com.ssafy.b105.auth.Jwt.domain.Jwt;
import com.ssafy.b105.auth.Jwt.domain.JwtAuthentication;
import com.ssafy.b105.auth.Jwt.domain.JwtAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
public class JwtFilter extends GenericFilterBean {


  private final Jwt jwt;

  private final String HEADER;

  public JwtFilter(Jwt jwt, String header) {
    this.jwt = jwt;
    HEADER = header;
  }

  @Override
  // 실제 필터링 로직 : 토큰의 인증 정보를 Security context에 저장
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    String jwtToken = resolveToekn(httpServletRequest);
    String requestURI = httpServletRequest.getRequestURI();
    // 토큰 유효성 검증
    if(SecurityContextHolder.getContext().getAuthentication() == null) {
      if (StringUtils.hasText(jwtToken)) {
        Jwt.Claims claims = verify(jwtToken);
        log.debug("Jwt parse result : {}",claims);

        // 만료 10분 전
        if(canRefresh(claims, 600L)) {
          String refreshToken = jwt.refreshToken(jwtToken);
          ((HttpServletResponse)servletResponse).setHeader(HEADER,refreshToken);
        }

        Long id = claims.getId();
        String principal = claims.getPrincipal();
        String name = claims.getNickname();
        List<GrantedAuthority> authorities = obtainAuthorities(claims);

        JwtAuthenticationToken authentication =
          new JwtAuthenticationToken(authorities, JwtAuthentication.of(id,principal,name), null);

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) servletRequest));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri : {}",
          authentication.getName(), requestURI);
      } else {
        log.debug("유효한 JWT 토큰이 없습니다. uri : {}", requestURI);
      }
    } else {
      log.debug("SecurityContextHolder not populated with security token, as it already contained: '{}'",
        SecurityContextHolder.getContext().getAuthentication());
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }

  private boolean canRefresh(Jwt.Claims claims, long seconds) {
    long exp = claims.exp();
    if(exp > 0) {
      long remain = exp - System.currentTimeMillis();
      return remain < seconds;
    }
    return false;
  }

  private Jwt.Claims verify(String jwtToken) {
    return jwt.verify(jwtToken);
  }

  // Request Header에서 토큰 정보를 꺼내오는 메서드
  private String resolveToekn(HttpServletRequest request) {
    String bearerToken = request.getHeader(HEADER);
    if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  private List<GrantedAuthority> obtainAuthorities(Jwt.Claims claims) {
    String[] roles = claims.getRoles();
    return roles == null || roles.length == 0 ?
      Collections.emptyList() :
      Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(toList());
  }
}
