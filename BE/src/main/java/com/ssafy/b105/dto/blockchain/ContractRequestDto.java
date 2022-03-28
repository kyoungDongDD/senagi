package com.ssafy.b105.dto.blockchain;

import java.math.BigInteger;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ContractRequestDto {

  private final Long deadLine;
  private final Long targetAmount;

}
