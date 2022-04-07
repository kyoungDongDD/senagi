package com.ssafy.b105.service.blockchain;

import com.ssafy.b105.dto.blockchain.NewWalletDto;
import com.ssafy.b105.entity.user.User;
import com.ssafy.b105.entity.blockchain.Wallet;
import com.ssafy.b105.repository.blockchain.TransactionRepository;
import com.ssafy.b105.entity.blockchain.wrapper.token.Token;
import com.ssafy.b105.repository.blockchain.WalletRepository;
import com.ssafy.b105.utils.BalanceConverter;
import com.ssafy.b105.utils.BlockchainConnector;
import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

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
  public Optional<Wallet> createAccount(User user) {
    try {
      NewWalletDto newWalletDto = connector.createAccount();
      Wallet wallet = Wallet.of(user, newWalletDto);
      return Optional.ofNullable(wallet);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.ofNullable(null);
  }

  @Override
  public String findAccountByUser(User user) {
    Wallet wallet = findByUser(user);
    return wallet.getAccount();
  }

  @Override
  public Long findBalanceByUser(User user) throws Exception {
    Wallet wallet = findByUser(user);
    BigInteger balance = tokenMgr.balanceOf(wallet.getAccount()).send();
    return BalanceConverter.bigIntegerToLong(balance, decimals);
  }

  private Wallet findByUser(User user) {
    return walletRepository.findWalletByUser(user)
        .orElseThrow(() -> new IllegalArgumentException());
  }


}
