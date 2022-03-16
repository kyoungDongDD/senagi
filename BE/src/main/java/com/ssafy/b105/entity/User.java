package com.ssafy.b105.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

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

  @Column(name = "name", length = 60)
  private String name;

  @Column(name = "nickname", length = 20,  unique = true)
  private String nickname;

  @Column(name = "type", length = 60)
  private UserType type;

  @Column(name = "registDate", length = 30)
  private LocalDateTime registDate;

  @Column(name = "account", length = 300)
  private String account;

  @Column(name = "phone", length = 60)
  private String phone;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_authority",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
  private Set<Authority> authorities;

}