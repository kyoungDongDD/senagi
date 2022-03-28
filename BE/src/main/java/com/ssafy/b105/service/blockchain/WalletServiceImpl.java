package com.ssafy.b105.service.blockchain;

import com.ssafy.b105.dto.blockchain.NewWalletDto;
import com.ssafy.b105.entity.blockchain.Transaction;
import com.ssafy.b105.entity.blockchain.Wallet;
import com.ssafy.b105.repository.TransactionRepository;
import com.ssafy.b105.repository.WalletRepository;
import com.ssafy.b105.utils.BalanceConverter;
import com.ssafy.b105.utils.BlockchainConnector;
import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.token.Token;

@Service
public class WalletServiceImpl implements WalletService {

  private final BlockchainConnector connector;
  private final WalletRepository walletRepository;
  private final TransactionRepository transactionRepository;

  private Token tokenMgr;

  private BigInteger decimals;

  public WalletServiceImpl(BlockchainConnector connector,
      WalletRepository walletRepository,
      TransactionRepository transactionRepository) {
    this.connector = connector;
    this.walletRepository = walletRepository;
    this.tokenMgr = connector.getTokenMgr();
    this.decimals = connector.getDecimals();
    this.transactionRepository = transactionRepository;
  }

  @Override
  public Optional<Wallet> createAccount(Long memberId) {
    try {
      NewWalletDto newWalletDto = connector.createAccount();
      Wallet wallet = Wallet.of(memberId, newWalletDto);
      walletRepository.save(wallet);
      return Optional.ofNullable(wallet);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.ofNullable(null);
  }

  @Override
  public String findAccountByMemberId(Long memberId) {
    Wallet wallet = findByMemberId(memberId);
    return wallet.getAccount();
  }

  @Override
  public Long findBalanceByMemberId(Long memberId) throws ExecutionException, InterruptedException {
    Wallet wallet = findByMemberId(memberId);
    BigInteger balance = tokenMgr.balanceOf(wallet.getAccount()).sendAsync().get();
    return BalanceConverter.bigIntegerToLong(balance, decimals);
  }

  @Override
  public Long chargeToken(Long memberId, Long amount)
      throws ExecutionException, InterruptedException {
    Wallet wallet = findByMemberId(memberId);
    TransactionReceipt receipt = tokenMgr.chargeToken(
            wallet.getAccount(),
            BalanceConverter.longToBigInteger(amount, decimals))
        .sendAsync().get();
    Transaction save = transactionRepository.save(Transaction.from(receipt));
    return 0L;
  }

  private Wallet findByMemberId(Long memberId) {
    return walletRepository.findWalletByMemberId(memberId)
        .orElseThrow(() -> new IllegalArgumentException());
  }


}
