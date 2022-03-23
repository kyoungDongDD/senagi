package com.ssafy.b105.utils;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.member.Member;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.token.Token;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

@Component
@RequiredArgsConstructor
public class BlockchainConnector {

  private final Web3j web3j;

  @Value("${eth.key.path}")
  private String keyPath;

  // Owner account
  @Value("${eth.admin.wallet}")
  private String adminWallet;

  @Value("${eth.admin.password}")
  private String adminPass;

  @Value("${eth.gas.limit}")
  private String gasLimit;

  @Value("${eth.gas.price}")
  private String gasPrice;

  @Value("${eth.contract.member}")
  private String memberAddr;

  @Value("${eth.contract.token}")
  private String tokenAddr;

  private Member memberMgr;
  private Token tokenMgr;
  private Credentials credentials;
  private ContractGasProvider gasProvider;


  @PostConstruct
  public void init() {
    this.gasProvider = new StaticGasProvider(
        new BigInteger(gasPrice),
        new BigInteger(gasLimit));

    try {
      File wallet = ResourceUtils.getFile(keyPath + adminWallet);
      credentials = WalletUtils.loadCredentials("pass0", wallet);
      this.memberMgr = Member.load(memberAddr, web3j, credentials, gasProvider);
      this.tokenMgr = Token.load(tokenAddr, web3j, credentials, gasProvider);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (CipherException e) {
      e.printStackTrace();
    }


  }


  public Member getMemberMgr() {
    return memberMgr;
  }

  public Token getTokenMgr() {
    return tokenMgr;
  }

  public String getOwnerAccount() {
    return credentials.getAddress();
  }

  public EthBlockNumber getBlockNumber()
      throws ExecutionException, InterruptedException {
    return web3j.ethBlockNumber().sendAsync().get();
  }

}
