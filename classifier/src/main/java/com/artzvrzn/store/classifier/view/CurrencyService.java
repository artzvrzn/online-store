package com.artzvrzn.store.classifier.view;

import com.artzvrzn.store.classifier.dao.api.CurrencyRepository;
import com.artzvrzn.store.classifier.dao.entity.CurrencyEntity;
import com.artzvrzn.store.classifier.model.Currency;
import com.artzvrzn.store.classifier.view.api.ICurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.artzvrzn.store.classifier.model.constant.BasicMessages.*;

@Service
public class CurrencyService implements ICurrencyService {

  @Autowired
  private CurrencyRepository repository;
  @Autowired
  private ConversionService cs;

  @Override
  public Currency get(String name) {
    CurrencyEntity entity = CrudUtils.getEntityOrThrow(name, repository);
    return cs.convert(entity, Currency.class);
  }

  @Override
  public Page<Currency> get(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
    return repository.findAll(pageable).map(e -> cs.convert(e, Currency.class));
  }

  @Override
  public Currency create(Currency dto) {
    if (dto == null) {
      throw new IllegalStateException(ERROR_CURRENCY_NOT_PASSED.getMessage());
    }
    CrudUtils.provideCreationTime(dto);
    CurrencyEntity entity = cs.convert(dto, CurrencyEntity.class);
    if (entity == null) {
      throw new IllegalStateException(ERROR_NULL_CONVERSION.getMessage());
    }
    repository.save(entity);
    return dto;
  }
}
