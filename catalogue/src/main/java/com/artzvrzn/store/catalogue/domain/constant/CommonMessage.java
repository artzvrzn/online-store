package com.artzvrzn.store.catalogue.domain.constant;

public enum CommonMessage {

  ERROR_INVALID_PARAMETERS("request contains invalid parameters"),
  ERROR_INNER_ERROR("server failed to process given parameters"),
  ERROR_UNEXPECTED("unknown error"),
  ERROR_RECORD_NOT_EXIST("record with id %s doesn't exist"),
  ERROR_RECORD_UPDATED("record has already been updated"),
  ERROR_RECORD_EXIST("record already exist"),
  ERROR_CATEGORY_NOT_PASSED("category has not been passed"),
  ERROR_CATEGORY_PARENT_SELF("category cannot be a parent to itself"),
  ERROR_CATEGORY_PARENT_CHILD("attempt to assign descendant category as a parent"),
  ERROR_CURRENCY_NOT_PASSED("currency has not been passed"),
  ERROR_NULL_CONVERSION("conversion has led to null value"),
  LOG_CREATED("{} has been created with id {}"),
  LOG_UPDATED("{} has been updated"),
  LOG_DELETED("{} has been deleted"),
  LOG_HTTP_REQUEST_ERROR("{} request to {} has returned the error (code: {}, message: {})"),
  LOG_REQUEST_SUCCESS("{} request to {}. response: {}"),
  LOG_ERROR("the error has occurred: '{}' {}");

  private final String message;

  CommonMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
