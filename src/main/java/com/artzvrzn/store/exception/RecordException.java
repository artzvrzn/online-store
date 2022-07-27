package com.artzvrzn.store.exception;

public class RecordException extends IllegalArgumentException {

  public RecordException() {
    super();
  }

  public RecordException(String message) {
    super(message);
  }

  public RecordException(Throwable throwable) {
    super(throwable);
  }

  public RecordException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
