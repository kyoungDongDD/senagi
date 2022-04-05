package com.ssafy.b105.auth;

import antlr.MismatchedCharException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode implements EnumModel {

   //Authority
  EXPIRED_TOKEN(403,"A001","expired token"),
  NON_LOGIN(403,"A002","non login"),
  INVALID_TOKEN(403,"A003","invalid token"),
  ACCESS_DENIED(403,"A004","access denied"),

  //todo: 로그인 에러 구현
  //login
  INVALID_PRINCIPAL(401,"B001","invalid principal"),
  INVALID_CREDENTIAL(401,"B002","invalid credential");

  private int status;
  private String code;
  private String message;

  ErrorCode(int status, String code, String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }

  @Override
  public String getKey() {
    return this.code;
  }

  @Override
  public String getValue() {
    return this.message;
  }
}

