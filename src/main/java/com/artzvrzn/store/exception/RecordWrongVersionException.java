package com.artzvrzn.store.exception;

public class RecordWrongVersionException extends RecordException {

  public RecordWrongVersionException() {
    super();
  }

  public RecordWrongVersionException(String message) {
    super(message);
  }

  public RecordWrongVersionException(Throwable throwable) {
    super(throwable);
  }

  public RecordWrongVersionException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
