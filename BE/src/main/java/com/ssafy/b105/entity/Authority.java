package com.ssafy.b105.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
  private List<UserAuthority> users = new ArrayList<>();

}
