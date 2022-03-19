package com.ssafy.b105.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(name = "principal", length = 60, unique = true)
  private String principal;

  @Column(name = "credential", length = 255)
  private String credential;

  @Column(name = "name", length = 60, unique = true)
  private String name;

  @Column(name = "regist_date", length = 30)
  @Builder.Default
  private LocalDateTime registDate = LocalDateTime.now();

  @Column(name = "account", length = 300)
  private String account;

  @Column(name = "phone", length = 100)
  private String phone;

  //연관 관계 매핑
  @OneToMany (mappedBy = "user",cascade = CascadeType.ALL)
  @Builder.Default
  private List<UserAuthority> userAuthorities= new ArrayList<>();

  //비지니스 메서드

}