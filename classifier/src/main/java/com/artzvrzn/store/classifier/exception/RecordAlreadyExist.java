package com.artzvrzn.store.classifier.exception;

public class RecordAlreadyExist extends RuntimeException {

  public RecordAlreadyExist(String message) {
    super(message);
  }

  public RecordAlreadyExist(String message, Throwable cause) {
    super(message, cause);
  }
}
