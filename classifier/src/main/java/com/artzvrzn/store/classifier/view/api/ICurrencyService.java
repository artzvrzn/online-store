package com.artzvrzn.store.classifier.view.api;

import com.artzvrzn.store.classifier.model.Currency;
import org.springframework.data.domain.Page;

public interface ICurrencyService {

  Currency get(String name);

  Page<Currency> get(int page, int size);

  Currency create(Currency dto);
}
