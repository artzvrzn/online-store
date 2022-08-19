package com.artzvrzn.store.classifier.converter;

import com.artzvrzn.store.classifier.dao.entity.CurrencyEntity;
import com.artzvrzn.store.classifier.model.Currency;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrencyEntityToDtoConverter implements Converter<Currency, CurrencyEntity> {

  @Override
  public CurrencyEntity convert(Currency dto) {
    CurrencyEntity entity = new CurrencyEntity();
    entity.setId(dto.getId());
    entity.setCreated(dto.getCreated());
    entity.setUpdated(dto.getUpdated());
    entity.setDescription(dto.getDescription());
    entity.setRate(dto.getRate());
    return entity;
  }
}
