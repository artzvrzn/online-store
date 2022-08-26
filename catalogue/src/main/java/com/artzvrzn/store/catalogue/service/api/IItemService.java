package com.artzvrzn.store.catalogue.service.api;

import com.artzvrzn.store.catalogue.model.Item;
import com.artzvrzn.store.catalogue.model.ItemQueryParams;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface IItemService {

  Item get(UUID id);

  Page<Item> get(int page, int size, ItemQueryParams params);

  Item create(Item dto);

  Item update(Item dto, UUID id, LocalDateTime updated);

  void delete(UUID id, LocalDateTime updated);
}
