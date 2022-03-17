package com.ssafy.b105.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {

  @Id
  @Column(name = "authority_name", length = 50)
  private String authorityName;

  @OneToMany(mappedBy = "authority")
  private Set<UserAuthority> users = new HashSet<UserAuthority>();

}
