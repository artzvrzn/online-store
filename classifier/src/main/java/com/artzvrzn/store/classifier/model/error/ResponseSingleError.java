package com.artzvrzn.store.classifier.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseSingleError {
  private String logref;
  private String message;
}
