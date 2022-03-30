package com.ssafy.b105.dto.blockchain;

import lombok.Getter;

@Getter
public class AmountDto {
  private final String transactionHash;
  private final Long amount;

  public AmountDto(String transactionHash, Long amount) {
    this.transactionHash = transactionHash;
    this.amount = amount;
  }
}
