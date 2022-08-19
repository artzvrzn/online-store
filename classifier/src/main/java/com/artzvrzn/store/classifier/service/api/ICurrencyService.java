package com.artzvrzn.store.classifier.service.api;

import com.artzvrzn.store.classifier.model.Currency;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;

public interface ICurrencyService {

  Currency get(String name);

  Page<Currency> get(int page, int size);

  List<Currency> getAll();

  Currency create(Currency dto);

  void updateRate(String name, BigDecimal value);

  void updateRates(Map<String, BigDecimal> rates);
}
