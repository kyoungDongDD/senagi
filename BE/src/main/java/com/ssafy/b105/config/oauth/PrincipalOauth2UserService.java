package com.ssafy.b105.config.oauth;


import com.ssafy.b105.config.auth.PrincipalDetails;
import com.ssafy.b105.config.oauth.provider.GoogleUserInfo;
import com.ssafy.b105.config.oauth.provider.OAuth2UserInfo;
import com.ssafy.b105.entity.User;
import com.ssafy.b105.entity.UserRole;
import com.ssafy.b105.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

  @Autowired
  private UserRepository userRepository;


  // userRequest 는 code를 받아서 accessToken을 응답 받은 객체
  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    //구글로그인 클릭 -> 구글로그인창 -> 구글로그인 완료 code를 리턴 ->AccessToken요청
    //userRequest 정보 -> loadUser호출 -> 구글 프로필 받아준다.
    OAuth2User oAuth2User = super.loadUser(userRequest); // google의 회원 프로필 조회
    return processOAuth2User(userRequest, oAuth2User);
  }

  private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
    //구글 로그인 요청~~
    // Attribute를 파싱해서 공통 객체로 묶는다. 관리가 편함.
    OAuth2UserInfo oAuth2UserInfo = null;
    if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
      oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
    }

    Optional<User> userOptional =
      userRepository.findByProviderAndProviderId(oAuth2UserInfo.getProvider(), oAuth2UserInfo.getProviderId());

    User user;
    if (userOptional.isPresent()) {
      user = userOptional.get();
      // user가 존재하면 update 해주기
//      user.setEmail(oAuth2UserInfo.getEmail());
      userRepository.save(user);
    } else {
      // user의 패스워드가 null이기 때문에 OAuth 유저는 일반적인 로그인을 할 수 없음.
      user = User.builder()
        .principal(oAuth2UserInfo.getEmail())
        .name(oAuth2UserInfo.getName())
        .role(UserRole.SUPPORTER)
        .build();


      userRepository.save(user);
    }
    return new PrincipalDetails(user, oAuth2User.getAttributes());
  }
}