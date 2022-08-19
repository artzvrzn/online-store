package com.artzvrzn.store.classifier.converter;

import com.artzvrzn.store.classifier.dao.entity.CurrencyEntity;
import com.artzvrzn.store.classifier.model.Currency;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrencyDtoToEntityConverter implements Converter<CurrencyEntity, Currency> {

  @Override
  public Currency convert(CurrencyEntity entity) {
    Currency dto = new Currency();
    dto.setName(entity.getId());
    dto.setCreated(entity.getCreated());
    dto.setUpdated(entity.getUpdated());
    dto.setDescription(entity.getDescription());
    dto.setRate(entity.getRate());
    return dto;
  }
}
