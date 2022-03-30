package com.ssafy.b105.dto.blockchain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NewWalletDto {
  private final String account;
  private final String fileName;
  private final String password;
}
