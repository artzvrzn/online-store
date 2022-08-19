package com.artzvrzn.store.classifier.service;

import com.artzvrzn.store.classifier.model.BaseDto;
import com.artzvrzn.store.classifier.model.constant.BasicMessages;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public final class CrudUtils {

  private CrudUtils() {};

  public static <T, ID> T getEntityOrThrow(ID id, CrudRepository<T, ID> repository) {
    Optional<T> optional = repository.findById(id);
    if (optional.isEmpty()) {
      throw new IllegalArgumentException(BasicMessages.ERROR_RECORD_NOT_EXIST.getMessage());
    }
    return optional.get();
  }

  public static <T extends BaseDto<?>> void provideCreationTime(T dto) {
    dto.setCreated(LocalDateTime.now(ZoneOffset.UTC));
    dto.setUpdated(dto.getCreated());
  }
}
