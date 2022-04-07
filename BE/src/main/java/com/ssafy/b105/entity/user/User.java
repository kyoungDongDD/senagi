package com.ssafy.b105.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.b105.auth.Jwt.domain.Jwt;
import com.ssafy.b105.auth.Jwt.domain.JwtAuthentication;
import com.ssafy.b105.auth.Jwt.domain.JwtAuthenticationToken;
import com.ssafy.b105.entity.SupportLog;
import com.ssafy.b105.entity.BaseEntity;
import com.ssafy.b105.entity.blockchain.Wallet;
import com.ssafy.b105.entity.campaign.Campaign;
import com.ssafy.b105.entity.common.MemberType;
import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User{

  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 60,unique = true)
  private String principal;


  private String credential;

  @Column(unique = true)
  private String name;

  @Builder.Default
  private LocalDateTime registDate = LocalDateTime.now();

  @Builder.Default
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @JsonIgnore
  private Set<Authority> authorities = new HashSet<>();

  private String phone;

  @Builder.Default
  private String vendor = "NBD";
  //연관 관계 매핑

  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL ,orphanRemoval = true)
  @Builder.Default
  private List<Campaign> campaigns = new ArrayList<>();

  @JsonIgnore
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Wallet wallet;

  //생성 매서드
  public static User of(String vendor, String principal, String nickname, String profileImageUrl) {
    return User.builder()
      .vendor(vendor)
      .name(nickname)
      .principal(principal)
      .build();
  }
  //비지니스 메서드
  public void encodePassword(PasswordEncoder passwordEncoder) {
    this.credential = passwordEncoder.encode(this.credential);
  }
  public Set<UserRole> getRoles() {
    return authorities.stream()
      .map(memberAuthority -> memberAuthority.getAuthority())
      .collect(Collectors.toSet());
  }
  public String newApiToken(Jwt jwt, JwtAuthenticationToken authentication) {
    JwtAuthentication principal = (JwtAuthentication) authentication.getPrincipal();
    Jwt.Claims claims = Jwt.Claims.of(
      principal.getId(),
      principal.getPrincipal(),
      principal.getName(),
      getRoleAtAuthorities(authentication).toArray(String[]::new)
    );
    return jwt.newToken(claims);
  }

  public String newApiToken(Jwt jwt, Authentication authentication) {
    Jwt.Claims claims = Jwt.Claims.of(
      id,
      principal,
      name,
      getRoleAtAuthorities(authentication).toArray(String[]::new)
    );
    return jwt.newToken(claims);
  }
  private static List<String> getRoleAtAuthorities(Authentication authentication) {
    return authentication.getAuthorities().stream()
      .map(GrantedAuthority::getAuthority)
      .collect(Collectors.toList());
  }
  public Set<GrantedAuthority> getGrantedAuthorities() {
    return this.authorities.stream()
      .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().name()))
      .collect(Collectors.toSet());
  }


  public User update(String email) {
    this.principal = email;
    return this;
  }

  public MemberType getMemberType() {
    if(authorities.contains(UserRole.SHELTER)) {
      return MemberType.Shelter;
    }
    if(authorities.contains(UserRole.SUPPORTER)) {
      return MemberType.Supporter;
    }
    return MemberType.None;
  }
  public void setWallet(Wallet wallet){
    this.wallet = wallet;
  }
}
