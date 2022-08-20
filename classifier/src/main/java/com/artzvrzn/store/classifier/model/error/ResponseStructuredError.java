package com.artzvrzn.store.classifier.model.error;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseStructuredError {
  private String logref;
  private List<Violation> violations;
}
