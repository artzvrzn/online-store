package com.artzvrzn.store.classifier.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExchangeRate {
  private LocalDateTime requestTime = LocalDateTime.now(ZoneOffset.UTC);
  @JsonProperty("base")
  private String baseCurrency;
  private Map<String, BigDecimal> rates;
}
