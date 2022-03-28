package com.ssafy.b105.service.blockchain;

import com.ssafy.b105.dto.blockchain.ChargeDto;
import com.ssafy.b105.entity.blockchain.Wallet;
import java.math.BigInteger;

public interface TokenContractService {

  ChargeDto charge(Wallet wallet, Long amount);

  Long balanceOf(Wallet wallet);

  String getTokenName();
  String getTokenSymbol();
  BigInteger getTokenDecimals();

}
