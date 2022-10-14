package com.artzvrzn.store.catalogue.service.api;

import com.artzvrzn.store.catalogue.dto.Dto;

public interface CRUDService<D extends Dto, ID> {

  D get(ID id);

  D create(D dto);

  D update(D dto, ID id);

  void delete(ID id);
}
