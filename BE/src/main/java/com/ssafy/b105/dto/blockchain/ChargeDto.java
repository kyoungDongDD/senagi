package com.ssafy.b105.dto.blockchain;

import lombok.Getter;

@Getter
public class ChargeDto {
  private final String transactionHash;
  private final Long amount;

  public ChargeDto(String transactionHash, Long amount) {
    this.transactionHash = transactionHash;
    this.amount = amount;
  }
}
