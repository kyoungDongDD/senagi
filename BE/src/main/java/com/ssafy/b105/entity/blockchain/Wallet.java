package com.ssafy.b105.entity.blockchain;

import com.ssafy.b105.dto.blockchain.NewWalletDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wallet {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "wallet_id")
  private Long id;

  // TODO Member와 연관관계 맺어야함
  private Long memberId;

  private String account;

  private String keyFileName;

  private String password;

  // TODO memberId -> Member
  public static Wallet of(Long memberId, NewWalletDto walletDto) {
    return new Wallet(null, memberId,
        walletDto.getAccount(), walletDto.getFileName(), walletDto.getPassword());
  }

}
