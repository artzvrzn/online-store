package com.artzvrzn.store.classifier.model.constant;

public enum BasicMessages {

  ERROR_RECORD_NOT_EXIST("record doesn't exist"),
  ERROR_RECORD_UPDATED("record has already been updated"),
  ERROR_CATEGORY_NOT_PASSED("category has not been passed"),
  ERROR_CURRENCY_NOT_PASSED("currency has not been passed"),
  ERROR_NULL_CONVERSION("conversion has led to null value"),
  LOG_CREATED("{} has been created with id {}"),
  LOG_UPDATED("{} has been updated"),
  LOG_DELETED("{} has been deleted");
  private final String message;

  BasicMessages(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
