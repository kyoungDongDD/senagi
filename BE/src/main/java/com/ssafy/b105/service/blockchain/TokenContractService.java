package com.ssafy.b105.service.blockchain;

import com.ssafy.b105.dto.blockchain.AmountDto;
import com.ssafy.b105.entity.blockchain.Wallet;
import java.math.BigInteger;

public interface TokenContractService {

  AmountDto charge(Wallet wallet, Long amount);

  Long balanceOf(Wallet wallet);

<<<<<<< HEAD
=======
  Long balanceOf(String account);

  BigInteger balanceOfBigInteger(Wallet wallet);


>>>>>>> dev
  String getTokenName();
  String getTokenSymbol();
  BigInteger getTokenDecimals();

}
<<<<<<< HEAD
=======

>>>>>>> dev
