package com.ssafy.b105.entity;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

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

  @Enumerated(EnumType.STRING)
  private UserRole role;

  private String phone;

  //연관 관계 매핑
  private String provider;

  private String providerId;

  //비지니스 메서드
  public void encodePassword(PasswordEncoder passwordEncoder) {
    this.credential = passwordEncoder.encode(this.credential);
  }
}