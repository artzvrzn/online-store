package com.artzvrzn.store.classifier.service;

import com.artzvrzn.store.classifier.dao.entity.BaseEntity;
import com.artzvrzn.store.classifier.model.BaseDto;
import com.artzvrzn.store.classifier.model.constant.CommonMessage;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import static com.artzvrzn.store.classifier.model.constant.CommonMessage.*;

public final class CrudUtils {

  private CrudUtils() {
  }

  public static <T, ID> T getEntityOrThrow(ID id, CrudRepository<T, ID> repository) {
    Optional<T> optional = repository.findById(id);
    if (optional.isEmpty()) {
      throw new IllegalArgumentException(String.format(ERROR_RECORD_NOT_EXIST.getMessage(), id));
    }
    return optional.get();
  }

  public static <T extends BaseDto> void provideCreationTime(T dto) {
    dto.setCreated(LocalDateTime.now(ZoneOffset.UTC));
    dto.setUpdated(dto.getCreated());
  }

  public static <T extends BaseEntity<?>> void optimisticBlockCheck(
      T entity, LocalDateTime updated
  ) {
    if (!entity.getUpdated().isEqual(updated)) {
      throw new IllegalStateException(CommonMessage.ERROR_RECORD_UPDATED.getMessage());
    }
  }

  public static <T extends BaseDto> void optimisticBlockCheck(T dto, LocalDateTime updated) {
    if (!dto.getUpdated().isEqual(updated)) {
      throw new IllegalStateException(CommonMessage.ERROR_RECORD_UPDATED.getMessage());
    }
  }
}
