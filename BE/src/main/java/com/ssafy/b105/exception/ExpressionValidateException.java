package com.ssafy.b105.exception;

public class ExpressionValidateException extends RuntimeException {
  //중복 검증 Exception
  public ExpressionValidateException(String message) {super(message);

  }
}