package com.artzvrzn.store.auth2.exception;

public class AuthenticationFailedException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "User doesn't exist or password is wrong";

  public AuthenticationFailedException(String message) {
    super(message);
  }

  public AuthenticationFailedException() {
    super(DEFAULT_MESSAGE);
  }
}
