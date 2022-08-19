package com.artzvrzn.store.classifier.listener;

import com.artzvrzn.store.classifier.dao.api.CurrencyRepository;
import com.artzvrzn.store.classifier.dao.entity.CurrencyEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(rollbackFor = Exception.class)
public class CurrencyCreatorOnStart {
  @Autowired
  private CurrencyRepository currencyRepository;

  @EventListener
  private void onApplicationStartEvent(ContextRefreshedEvent event) {
    List<String> existCurrencies =
        currencyRepository.findAll().stream()
            .map(CurrencyEntity::getId)
            .map(String::toUpperCase)
            .collect(Collectors.toList());

    Arrays.stream(BaseCurrency.values())
        .filter(e -> existCurrencies.contains(e.name()))
        .forEach(this::persistEntity);
  }

  private void persistEntity(BaseCurrency baseCurrency) {
    CurrencyEntity currency = new CurrencyEntity();
    currency.setId(baseCurrency.name());
    currency.setDescription(baseCurrency.getDescription());
    currency.setCreated(LocalDateTime.now(ZoneOffset.UTC));
    currency.setUpdated(currency.getCreated());
    currency.setRate(new BigDecimal(1));
    currencyRepository.save(currency);
  }

  private enum BaseCurrency {
    USD("Доллар США"),
    EUR("Евро"),
    BYN("Белорусский рубль");

    private final String description;

    BaseCurrency(String description) {
      this.description = description;
    }

    public String getDescription() {
      return description;
    }
  }
}
