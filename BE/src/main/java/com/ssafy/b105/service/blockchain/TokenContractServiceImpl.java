package com.ssafy.b105.service.blockchain;

import com.ssafy.b105.dto.blockchain.AmountDto;
import com.ssafy.b105.entity.blockchain.Wallet;
import com.ssafy.b105.entity.blockchain.wrapper.token.Token;
import com.ssafy.b105.utils.BalanceConverter;
import com.ssafy.b105.utils.BlockchainConnector;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Service
@Transactional
public class TokenContractServiceImpl implements
    TokenContractService {

  private final BlockchainConnector blockchainConnector;

  private Token tokenMgr;
  private BigInteger decimals;

  public TokenContractServiceImpl(BlockchainConnector blockchainConnector) {
    this.blockchainConnector = blockchainConnector;
    this.tokenMgr = blockchainConnector.getTokenMgr();
    decimals = blockchainConnector.getDecimals();
  }

  @Override
  public AmountDto charge(Wallet wallet, Long amount) {
    if(amount <= 0) throw new IllegalArgumentException("Amount 0보다 커야합니다.");
    try {
      BigInteger chargedAmount = BalanceConverter.longToBigInteger(amount, decimals);
      TransactionReceipt receipt = tokenMgr.chargeToken(wallet.getAccount(),
          chargedAmount)
          .sendAsync().get();
      wallet.chargeBalance(chargedAmount);
      return new AmountDto(
          receipt.getTransactionHash(),
          BalanceConverter.bigIntegerToLong(wallet.getBalance(),decimals));
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Long balanceOf(Wallet wallet) {
    try {
      return BalanceConverter.bigIntegerToLong(tokenMgr.balanceOf(wallet.getAccount()).sendAsync().get(),decimals);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return 0L;
  }

  @Override
  public String getTokenName() {
    try {
      return tokenMgr.name().sendAsync().get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return "";
  }

  @Override
  public String getTokenSymbol() {
    try {
      return tokenMgr.symbol().sendAsync().get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return "";
  }

  @Override
  public BigInteger getTokenDecimals() {
    return decimals;
  }
}