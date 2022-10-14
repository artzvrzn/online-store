package com.artzvrzn.store.catalogue.service.util;

import static com.artzvrzn.store.catalogue.domain.constant.CommonMessage.ERROR_RECORD_NOT_EXIST;

import com.artzvrzn.store.catalogue.domain.Entity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public final class CrudUtils {

  private CrudUtils() {}

  public static <T extends Entity<ID>, ID> T getEntityOrThrow(ID id, CrudRepository<T, ID> repository) {
    Optional<T> optional = repository.findById(id);
    if (optional.isEmpty()) {
      throw new IllegalArgumentException(String.format(ERROR_RECORD_NOT_EXIST.getMessage(), id));
    }
    return optional.get();
  }
}
