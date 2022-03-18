package com.ssafy.b105.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {

  @Id
  @Column(name = "authority_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long authorityId;

  @Column(name = "authority_name", length = 50)
  private String authorityName;

  @OneToMany(mappedBy = "authority")
  private Set<UserAuthority> users = new HashSet<UserAuthority>();

}
