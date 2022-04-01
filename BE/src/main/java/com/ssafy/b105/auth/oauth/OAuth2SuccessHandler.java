package com.ssafy.b105.auth.oauth;

import com.ssafy.b105.auth.Jwt.domain.Jwt;
import com.ssafy.b105.entity.user.User;
import com.ssafy.b105.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final Jwt jwt;

  private final UserRepository userRepository;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

    Map<String, Object> attributes = ((DefaultOAuth2User) authentication.getPrincipal()).getAttributes();
    String email = (String) attributes.get("email");
    User user = userRepository.findByPrincipal(email).orElseThrow();
    String token = user.newApiToken(jwt,authentication);

    getRedirectStrategy().sendRedirect(request,response,
      "https://j6b105.p.ssafy.io"+"?jwtToken="+token); // 리다이렉트 페이지 추가
  }
}
