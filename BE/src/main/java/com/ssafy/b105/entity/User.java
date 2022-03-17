package com.ssafy.b105.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
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

  @Column(name = "name", length = 60, unique = true)
  private String name;

  @Enumerated(EnumType.STRING) // 반환타입 int(Default) -> String 으로 바꿔줌
  @Column(name = "type", length = 60)
  private UserType type;

  @Column(name = "regist_date", length = 30)
  private LocalDateTime registDate = LocalDateTime.now();

  @Column(name = "account", length = 300)
  private String account;

  @Column(name = "phone", length = 100)
  private String phone;

  //연관 관계 매핑
  @OneToMany (mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true) //고아객체 삭제 넣었는데 수정삭제가 없는데 필요할까?
  private Set<UserAuthority> authorities = new HashSet<UserAuthority>();

  //비지니스 메서드

}