package com.artzvrzn.store.view.api;

import com.artzvrzn.store.dao.entity.BaseEntity;
import com.artzvrzn.store.exception.RecordNotFoundException;
import com.artzvrzn.store.exception.RecordWrongVersionException;
import com.artzvrzn.store.model.BaseDto;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CRUDService<E extends BaseEntity> {

  protected final ConversionService conversionService;
  protected final JpaRepository<E, UUID> repository;

  protected CRUDService(ConversionService conversionService, JpaRepository<E, UUID> repository) {
    this.conversionService = conversionService;
    this.repository = repository;
  }

  protected <D extends BaseDto> D assignIdAndTime(D dto) {
    LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.UTC);
    dto.setCreated(currentTime);
    dto.setUpdated(currentTime);
    dto.setId(UUID.randomUUID());
    return dto;
  }

  protected <F, R> R convertOrThrow(F from, Class<R> resultClass) {
    R result = conversionService.convert(from, resultClass);
    if (result == null) {
      throw new IllegalStateException("Conversion failed (null value)");
    }
    return result;
  }

  protected E getEntityOrThrow(UUID id) {
    Optional<E> optional = repository.findById(id);
    if (optional.isEmpty()) {
      throw new RecordNotFoundException("Record not found");
    }
    return optional.get();
  }

  protected E getEntityOrThrow(UUID id, LocalDateTime updated) {
    E entity = getEntityOrThrow(id);
    if (!entity.getUpdated().isEqual(updated)) {
      throw new RecordWrongVersionException("Record has been already updated");
    }
    return entity;
  }
}
