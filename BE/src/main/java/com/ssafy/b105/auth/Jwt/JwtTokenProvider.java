package com.ssafy.b105.auth.Jwt;
import com.ssafy.b105.auth.Jwt.domain.AuthenticationRequest;;
import com.ssafy.b105.auth.Jwt.domain.AuthenticationResult;
import com.ssafy.b105.auth.Jwt.domain.Jwt;
import com.ssafy.b105.auth.Jwt.domain.JwtAuthentication;
import com.ssafy.b105.auth.Jwt.domain.JwtAuthenticationToken;
import com.ssafy.b105.entity.user.User;
import com.ssafy.b105.exception.ExpressionValidateException;
import com.ssafy.b105.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import static org.springframework.util.TypeUtils.isAssignable;

@Component
@RequiredArgsConstructor
@Slf4j
// 토큰 생성, 유효성 검증 담당
public class JwtTokenProvider implements AuthenticationProvider {

  private final Jwt jwt;

  private final UserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    // Authentication에는 추가 기능이 많아서 principal, credentials만 전송
    JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
    return processUserAuthentication(new AuthenticationRequest(
      String.valueOf(authenticationToken.getPrincipal()),
      authenticationToken.getCredentials()));
  }

  /**
   * {@link org.springframework.security.authentication.ProviderManager#authenticate} 메소드에서 호출된다.
   *
   * true 를 리턴하면 현재 Provider 에서 인증 처리를 할 수 있음을 의미한다.
   * 본 Provider 에서는 {@link com.ssafy.b105.auth.Jwt.domain.JwtAuthenticationToken} 타입의 {@link Authentication} 를 처리할 수 있다.
   */
  @Override
  public boolean supports(Class<?> authentication) {
    return isAssignable(JwtAuthenticationToken.class, authentication);
  }

  private Authentication processUserAuthentication(AuthenticationRequest request) {
    try {
      User user = userService.login(request.getPrincipal(), request.getCredentials());
      JwtAuthenticationToken authentication =
        // 응답용 Authentication 인스턴스를 생성한다.
        // JwtAuthenticationToken.principal 부분에는 JwtAuthentication 인스턴스가 set 된다.
        // 로그인 완료 전 JwtAuthenticationToken.principal 부분은 Email 인스턴스가 set 되어 있었다.

        new JwtAuthenticationToken(user.getGrantedAuthorities(), JwtAuthentication.of(user.getId(), user.getPrincipal(), user.getName()), null);
      // JWT 값을 생성한다.
      // 권한은 ROLE_USER 를 부여한다.
      String apiToken = user.newApiToken(jwt,authentication);
      authentication.setDetails(AuthenticationResult.from(apiToken, user));
      return authentication;
    }
    catch (ChangeSetPersister.NotFoundException e) {  // 일치하는 아이디 없음
      log.error("일치하는 아이디가 없습니다. msg : "+e.getMessage());
      throw new BadCredentialsException(e.getMessage()) ;
    }
    catch (IllegalArgumentException e) {              // 비밀번호가 틀림
      log.error("일치하는 비밀번호가 틀립니다. msg : "+e.getMessage());
      throw new BadCredentialsException(e.getMessage());
    }
    catch (DataAccessException e) {                   // null 리턴할시
      log.error("로그인 정보가 없습니다. msg : "+e.getMessage());
      throw new AuthenticationServiceException(e.getMessage(), e);
    }
  }
}
