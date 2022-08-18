package com.artzvrzn.store.classifier.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseBody
  public String illegalArgumentHandler(IllegalArgumentException exc) {
    return exc.getMessage();
  }

}
