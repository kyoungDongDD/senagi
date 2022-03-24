package com.ssafy.b105.service.blockchain;

import com.ssafy.b105.entity.blockchain.Wallet;
import java.util.concurrent.ExecutionException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WalletServiceImplTest {

  @Autowired
  WalletServiceImpl walletService;

  Long memberId;
  Wallet wallet;
  @BeforeEach
  public void createNewAccount() {
    memberId = 1L;
    wallet = walletService.createAccount(memberId).get();
  }

  @Test
  public void 계정_생성_확인() {
    String account = walletService.findAccountByMemberId(memberId);
    // then
    Assertions.assertThat(wallet).isNotNull();
    Assertions.assertThat(wallet.getAccount()).isEqualTo(account);
  }

  @Test
  public void 계정_잔액_확인() throws ExecutionException, InterruptedException {
    // when
    Long balance = walletService.findBalanceByMemberId(memberId);
    // then
    Assertions.assertThat(balance).isEqualTo(0L);
  }

}