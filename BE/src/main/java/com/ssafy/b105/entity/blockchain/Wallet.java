package com.ssafy.b105.entity.blockchain;

import com.ssafy.b105.dto.blockchain.NewWalletDto;
import com.ssafy.b105.entity.user.User;
import java.math.BigInteger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(length =  40)
  private String account;

  private String keyFileName;

  @Column(length = 32)
  private String password;

  private BigInteger balance;


  public static Wallet of(User user, NewWalletDto walletDto) {
    return new Wallet(null, user,
        walletDto.getAccount(), walletDto.getFileName(), walletDto.getPassword(),BigInteger.ZERO);
  }

  public void chargeBalance(BigInteger amount) {
    this.balance = balance.add(amount);
  }

  public void dischargeBalance(BigInteger amount) { this.balance = balance.subtract(amount);}

  public void setBalance(BigInteger amount) {this.balance = amount;}

}
