package com.ssafy.b105.service.blockchain;

import com.ssafy.b105.entity.blockchain.Wallet;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface WalletService {

  Optional<Wallet> createAccount(Long memberId);

  String findAccountByMemberId(Long memberId);

  Long findBalanceByMemberId(Long memberId) throws ExecutionException, InterruptedException;

  Long chargeToken(Long memberId, Long amount)
      throws ExecutionException, InterruptedException;
}
