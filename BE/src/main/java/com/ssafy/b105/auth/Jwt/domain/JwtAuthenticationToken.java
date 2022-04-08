package com.ssafy.b105.auth.Jwt.domain;

import com.ssafy.b105.auth.Jwt.JwtFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Slf4j
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
  /**
   * 인증 주체이며 Object타입이다.
   * 로그인 전에는 String, 로그인 후에는 {@link JwtAuthentication} 타입이다.
   * 생성 시점 : {@link JwtFilter#doFilter}
   *
   * 컨트롤러에서 {@link org.springframework.security.core.annotation.AuthenticationPrincipal} 을 사용해 쉽게 접근할 수 있다.
   * 단 인증이 정상적으로 완료된 경우에만 사용해야한다. -> 로그인 후 내 정보 확인 등
   */

  private final Object principal;

  private String credentials;

  public JwtAuthenticationToken(Object principal, String credentials) {
    super(null);
    super.setAuthenticated(false);

    if(!principal.toString().isEmpty())log.debug("아이디가 입력되었습니다.");  // 아이디랑 비밀번호 정보는 내면 안될것같음
    if (!credentials.isEmpty())log.debug("비밀번호가 입력되었습니다.");
    this.principal = principal;
    this.credentials = credentials;
  }

  public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal, String credentials) {
    super(authorities);
    super.setAuthenticated(true);

    this.principal = principal;
    this.credentials = credentials;
  }

  // 부모 클래스에서 해당 메서드가 public이기 때문에 외부 사용자가 true 설정하는 것을 막기위해서.
  // 생성자에서만 접근 가능하도록함.
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    if (isAuthenticated) {
      throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
    }
    super.setAuthenticated(false);
  }

  @Override
  public String getCredentials() {
    return credentials;
  }

  @Override
  public Object getPrincipal() {
    return principal;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
