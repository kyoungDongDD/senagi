package com.ssafy.b105.utils;

import java.math.BigInteger;

public class BalanceConverter {

  public static long bigIntegerToLong(BigInteger balance, BigInteger decimals) {
    return balance.divide(decimals).longValue();
  }

  public static BigInteger longToBigInteger(long balance, BigInteger decimals) {
    return BigInteger.valueOf(balance).multiply(decimals);
  }

}
