package com.ssafy.b105.entity.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "AUTHORITY_ID")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "User_ID")
  private User user;

  @Enumerated(value = EnumType.STRING)
  private UserRole authority;

  protected Authority(UserRole authority) {
    this.authority = authority;
  }
  // 생성 메서드
  public static Authority from(UserRole role) {
    return new Authority(role);
  }

  // 연관관계 메서드
  public void setUser(User user) {
    this.user = user;
    this.user.getAuthorities().add(this);
  }
}

