package com.ssafy.b105.exception;

public class DuplicateException extends RuntimeException{
  //중복 검증 Exception
  public DuplicateException(String message) {
    super(message);
  }

}
