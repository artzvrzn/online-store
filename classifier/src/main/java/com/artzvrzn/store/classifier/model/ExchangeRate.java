package com.artzvrzn.store.classifier.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExchangeRate {
  private LocalDateTime requestTime = LocalDateTime.now(ZoneOffset.UTC);
  @JsonProperty("base")
  private String baseCurrency;
  private Map<String, BigDecimal> rates;
}
