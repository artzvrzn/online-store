package com.artzvrzn.store.classifier.service.api;

import com.artzvrzn.store.classifier.model.Currency;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ICurrencyService {

  Currency get(String name);

  List<Currency> getAll();

  Currency create(Currency dto);

  Currency update(Currency dto, String name, LocalDateTime updated);

  void updateRates(Map<String, BigDecimal> rates);
}
