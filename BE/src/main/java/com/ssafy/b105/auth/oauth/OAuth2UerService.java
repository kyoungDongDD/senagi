package com.ssafy.b105.auth.oauth;


import com.ssafy.b105.entity.blockchain.Wallet;
import com.ssafy.b105.entity.common.MemberType;
import com.ssafy.b105.entity.user.Authority;
import com.ssafy.b105.entity.user.User;
import com.ssafy.b105.entity.user.UserRole;
import com.ssafy.b105.repository.UserRepository;
import com.ssafy.b105.service.blockchain.MemberContractService;
import com.ssafy.b105.service.blockchain.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


import java.util.Collections;

@Service
@RequiredArgsConstructor
public class OAuth2UerService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final UserRepository userRepository;
  private final WalletService walletService;
  private final MemberContractService memberContractService;


  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = delegate.loadUser(userRequest);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();


    OAuthAttributes attributes = OAuthAttributes.of(registrationId,
      userNameAttributeName,
      oAuth2User.getAttributes());

    saveOrUpdate(registrationId,attributes);

    return new DefaultOAuth2User(
      Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
      attributes.getAttributes(),
      attributes.getNameAttributeKey());
  }

  private User saveOrUpdate(String registrationId, OAuthAttributes attributes) {
    User user =  userRepository.findByPrincipalAndVendor( attributes.getEmail(), registrationId)

      .map(entity -> entity.update(attributes.getEmail()))
      .orElseGet(() -> {
        User entity = User.of(
          registrationId,
          attributes.getEmail(),
          attributes.getName(),
          attributes.getPicture());
        Authority authority = Authority.from(UserRole.SUPPORTER);
        authority.setUser(entity);

        Wallet wallet = null;
        try {
          wallet = walletService.createAccount(entity)
            .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        } catch (ChangeSetPersister.NotFoundException e) {
          e.printStackTrace();
        }
        entity.setWallet(wallet);
        memberContractService.registMember(wallet.getAccount(), MemberType.Supporter);
        return userRepository.save(entity);
      });
    return user;
  }
}