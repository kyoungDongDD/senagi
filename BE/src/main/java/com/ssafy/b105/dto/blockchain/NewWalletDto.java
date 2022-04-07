package com.ssafy.b105.dto.blockchain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class NewWalletDto {
  private final String account;
  private final String fileName;
  private final String password;

  public NewWalletDto(String account, String fileName, String password) {
    this.account = "0x" + account;
    this.fileName = fileName;
    this.password = password;
  }
}
