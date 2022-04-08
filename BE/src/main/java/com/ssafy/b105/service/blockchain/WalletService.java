package com.ssafy.b105.service.blockchain;

<<<<<<< HEAD
import com.ssafy.b105.entity.User;
=======
import com.ssafy.b105.entity.user.User;
>>>>>>> dev
import com.ssafy.b105.entity.blockchain.Wallet;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface WalletService {

  Optional<Wallet> createAccount(User user);

  String findAccountByUser(User user);

<<<<<<< HEAD
  Long findBalanceByUser(User user) throws ExecutionException, InterruptedException;
=======
  Long findBalanceByUser(User user) throws Exception;
>>>>>>> dev

}
