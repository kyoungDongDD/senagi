package com.ssafy.b105.service.blockchain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.ssafy.b105.dto.blockchain.ChargeDto;
import com.ssafy.b105.entity.User;
import com.ssafy.b105.entity.blockchain.Wallet;
import com.ssafy.b105.entity.common.MemberType;
import com.ssafy.b105.repository.UserRepository;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TokenContractServiceTest {

  @Autowired
  UserRepository userRepository;

  @Autowired
  MemberContractService memberContractService;

  @Autowired
  TokenContractService tokenContractService;

  @Autowired
  WalletService walletService;

  @Test
  public void 토큰_유효_충전() throws ExecutionException, InterruptedException {
    // given
    User user = User.builder()
        .principal("user1@ssafy.io")
        .name("Member1")
        .build();

    userRepository.save(user);

    Wallet wallet = walletService.createAccount(user).get();

    memberContractService.registMember(wallet.getAccount(), MemberType.Supporter);

    // when
    ChargeDto chargeDto = tokenContractService.charge(wallet, 1000L);
    Long findBalance = tokenContractService.balanceOf(wallet);

    // then
    assertThat(chargeDto.getAmount()).isEqualTo(1000L);
    assertThat(chargeDto.getAmount()).isEqualTo(findBalance);
  }

}



