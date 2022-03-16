package com.ssafy.b105.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
  private String type;

  @Column(name = "registDate", length = 60)
  private LocalDateTime registDate;

  @Column(name = "account", length = 300)
  private String account;

  @Column(name = "phone", length = 60)
  private String phone;

}