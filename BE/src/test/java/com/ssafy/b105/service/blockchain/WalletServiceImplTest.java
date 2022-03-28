package com.ssafy.b105.service.blockchain;

import static org.assertj.core.api.Assertions.*;

import com.ssafy.b105.entity.User;
import com.ssafy.b105.entity.blockchain.Wallet;
import com.ssafy.b105.entity.common.MemberType;
import com.ssafy.b105.repository.UserRepository;
import com.ssafy.b105.service.UserService;
import java.util.concurrent.ExecutionException;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class WalletServiceImplTest {

  @Autowired
  WalletServiceImpl walletService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  MemberContractService memberContractService;

  @Test
  public void 새로운_지갑_생성() throws NotFoundException {
    // given
    User user = User.builder()
        .principal("user1@ssafy.io")
        .name("Member1")
        .build();

    userRepository.save(user);

    Wallet wallet = walletService.createAccount(user)
        .orElseThrow(() -> new NotFoundException());

    // when

    String account = walletService.findAccountByUser(user);

    // then
    assertThat(account).isEqualTo(wallet.getAccount());
  }

  @Test
  public void 계정_잔액_확인() throws ExecutionException, InterruptedException {
    // given
    User user = User.builder()
        .principal("user1@ssafy.io")
        .name("Member1")
        .build();

    userRepository.save(user);

    Wallet wallet = walletService.createAccount(user).get();

    // when
    Long balance = walletService.findBalanceByUser(user);
    // then
    assertThat(balance).isEqualTo(0L);
  }

}