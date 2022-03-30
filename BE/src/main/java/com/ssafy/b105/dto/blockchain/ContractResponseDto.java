package com.ssafy.b105.dto.blockchain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ContractResponseDto {

  private final String account;
  private final String blockHash;

}
