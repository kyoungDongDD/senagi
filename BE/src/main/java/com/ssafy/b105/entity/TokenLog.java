package com.ssafy.b105.entity;

import com.ssafy.b105.entity.user.User;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenLog {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "token_log_id")
  private Long id;

  private Long amount;

  private boolean isProcess;
  private LocalDateTime processDate;

  private LocalDateTime registDate;

  private String txHash;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  public static TokenLog of(Long amount, String txHash, User user) {
    return new TokenLog(null, amount, false, null, LocalDateTime.now(),txHash,user);
  }

  public void withdrawalProcess() {
    this.isProcess = true;
    processDate = LocalDateTime.now();
  }

}
