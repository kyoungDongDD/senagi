package com.ssafy.b105.dto.blockchain;

public class ContractCloseResponseDto {
  private final String transactionHash;
  private final Long fromAmount;
  private final Long toAmount;

  public ContractCloseResponseDto(String transactionHash, Long fromAmount, Long toAmount) {
    this.transactionHash = transactionHash;
    this.fromAmount = fromAmount;
    this.toAmount = toAmount;
  }
}

