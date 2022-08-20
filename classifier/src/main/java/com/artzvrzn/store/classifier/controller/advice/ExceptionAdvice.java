package com.artzvrzn.store.classifier.controller.advice;

import com.artzvrzn.store.classifier.exception.RecordAlreadyExist;
import com.artzvrzn.store.classifier.model.error.ResponseSingleError;
import com.artzvrzn.store.classifier.model.error.ResponseStructuredError;
import com.artzvrzn.store.classifier.model.error.Violation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.artzvrzn.store.classifier.model.constant.CommonMessage.*;

@ControllerAdvice
@Log4j2
public class ExceptionAdvice {
  private static final String ERROR = "error";
  private static final String STRUCTURED_ERROR = "structured_error";

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseStructuredError methodArgumentExceptionHandler(MethodArgumentNotValidException e) {
    List<Violation> violations =
        e.getBindingResult().getFieldErrors()
            .stream().map(v -> new Violation(v.getField(), v.getDefaultMessage()))
            .collect(Collectors.toList());
    log.error(LOG_ERROR.getMessage(), e.getClass().getSimpleName(), e.getMessage());
    return new ResponseStructuredError(STRUCTURED_ERROR, violations);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseStructuredError constraintExceptionHandler(ConstraintViolationException e) {
    List<Violation> violations =
        e.getConstraintViolations()
            .stream()
            .map(v -> new Violation(v.getPropertyPath().toString(), v.getMessage()))
            .collect(Collectors.toList());
    log.error(LOG_ERROR.getMessage(), e.getClass().getSimpleName(), e.getMessage());
    return new ResponseStructuredError(STRUCTURED_ERROR, violations);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseSingleError illegalArgumentHandler(IllegalArgumentException e) {
    log.error(LOG_ERROR.getMessage(), e.getClass().getSimpleName(), e.getMessage());
    return new ResponseSingleError(ERROR, e.getMessage());
  }

  @ExceptionHandler(IllegalStateException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseSingleError illegalStateExceptionHandler(IllegalStateException e) {
    log.error(LOG_ERROR.getMessage(), e.getClass().getSimpleName(), e.getMessage());
    return new ResponseSingleError(ERROR, e.getMessage());
  }

  @ExceptionHandler(RecordAlreadyExist.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseSingleError recordExistExceptionHandler(RecordAlreadyExist e) {
    log.error(LOG_ERROR.getMessage(), e.getClass().getSimpleName(), e.getMessage());
    return new ResponseSingleError(ERROR, ERROR_RECORD_EXIST.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseSingleError otherExceptionHandler(Exception e) {
    log.error(LOG_ERROR.getMessage(), e.getClass().getSimpleName(), e.getMessage());
    return new ResponseSingleError(ERROR, ERROR_UNEXPECTED.getMessage());
  }
}
