package com.ssafy.b105.dto.blockchain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

<<<<<<< HEAD
@RequiredArgsConstructor
=======
>>>>>>> dev
@Getter
public class NewWalletDto {
  private final String account;
  private final String fileName;
  private final String password;
<<<<<<< HEAD
=======

  public NewWalletDto(String account, String fileName, String password) {
    this.account = "0x" + account;
    this.fileName = fileName;
    this.password = password;
  }
>>>>>>> dev
}
