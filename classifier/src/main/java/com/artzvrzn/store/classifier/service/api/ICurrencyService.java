package com.artzvrzn.store.classifier.service.api;

import com.artzvrzn.store.classifier.model.Currency;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ICurrencyService {

  Currency get(String name);

  List<Currency> getAll();

  boolean isExist(String name);

  Currency create(Currency dto);

  Currency update(Currency dto, String name, LocalDateTime updated);

  void updateRates(Map<String, BigDecimal> rates);
}
