package com.artzvrzn.store.classifier.service;

import com.artzvrzn.store.classifier.dao.api.CurrencyRepository;
import com.artzvrzn.store.classifier.dao.entity.CurrencyEntity;
import com.artzvrzn.store.classifier.model.Currency;
import com.artzvrzn.store.classifier.service.api.ICurrencyService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.artzvrzn.store.classifier.model.constant.BasicMessages.*;

@Service
@Transactional(rollbackFor = Exception.class)
@Log4j2
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
  public List<Currency> getAll() {
    return repository.findAll()
        .stream()
        .map(e -> cs.convert(e, Currency.class))
        .collect(Collectors.toList());
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

  @Override
  public void updateRate(String name, BigDecimal value) {
    CurrencyEntity entity = CrudUtils.getEntityOrThrow(name, repository);
    entity.setRate(value);
    repository.save(entity);
  }

  @Override
  public void updateRates(Map<String, BigDecimal> rates) {
    List<CurrencyEntity> currencyEntities = repository.findAll();
    for (CurrencyEntity entity: currencyEntities) {
      if (rates.containsKey(entity.getId())) {
        entity.setRate(rates.get(entity.getId()));
        entity.setUpdated(LocalDateTime.now(ZoneOffset.UTC));
      }
    }
    repository.saveAll(currencyEntities);
    log.info("exchange rates has been updated");
  }
}
