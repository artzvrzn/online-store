package com.artzvrzn.store.classifier.service;

import com.artzvrzn.store.classifier.dao.api.CurrencyRepository;
import com.artzvrzn.store.classifier.dao.entity.CurrencyEntity;
import com.artzvrzn.store.classifier.exception.RecordAlreadyExist;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.artzvrzn.store.classifier.model.constant.CommonMessage.*;

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
    if (repository.findById(dto.getName()).isPresent()) {
      throw new RecordAlreadyExist(ERROR_RECORD_EXIST.getMessage());
    }
    CrudUtils.provideCreationTime(dto);
    CurrencyEntity entity = cs.convert(dto, CurrencyEntity.class);
    if (entity == null) {
      throw new IllegalStateException(ERROR_NULL_CONVERSION.getMessage());
    }
    repository.save(entity);
    log.debug(LOG_CREATED.getMessage(), "currency", entity.getId());
    return dto;
  }

  @Override
  public Currency update(Currency dto, String name, LocalDateTime updated) {
    CurrencyEntity entity = CrudUtils.getEntityOrThrow(name, repository);
    CrudUtils.optimisticBlockCheck(entity, updated);
    entity.setUpdated(LocalDateTime.now(ZoneOffset.UTC));
    entity.setDescription(dto.getDescription());
    entity.setRate(dto.getRate());
    repository.save(entity);
    log.debug(LOG_UPDATED.getMessage(), "currency", name);
    return dto;
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
    log.info(LOG_UPDATED.getMessage(), "exchange rates");
  }
}
