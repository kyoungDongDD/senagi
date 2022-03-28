package com.ssafy.b105.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.member.Member;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.token.Token;

@SpringBootTest
class BlockchainConnectorTest {

  @Autowired
  private BlockchainConnector blockchainConnector;

  @Value("${eth.admin.account}")
  private String adminAccount;

  @Test
  public void getBlockNumber() {
    // given
    try {
      EthBlockNumber blockNumber = blockchainConnector.getBlockNumber();
      System.out.println("blockNumber = " + blockNumber.getBlockNumber());
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void check_SymbolAndNameByTokenMgr() throws ExecutionException, InterruptedException {
    // given
    Token tokenMgr = blockchainConnector.getTokenMgr();

    // when
    String symbol = tokenMgr.symbol().sendAsync().get();
    String name = tokenMgr.name().sendAsync().get();
        
    // then
    Assertions.assertThat(symbol).isEqualTo("톨");
    Assertions.assertThat(name).isEqualTo("사료");
  }

  @Test
  public void check_adminAccount() {
    // given
    String ownerAccount = blockchainConnector.getOwnerAccount();

    // then
    Assertions.assertThat(ownerAccount).isEqualTo(adminAccount);
  }

}