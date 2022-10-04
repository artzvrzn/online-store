package com.artzvrzn.store.catalogue.service.api;

import com.artzvrzn.store.catalogue.dto.request.ItemRequest;
import com.artzvrzn.store.catalogue.dto.response.ItemResponse;
import com.artzvrzn.store.catalogue.domain.ItemQueryParams;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface ItemService {

  ItemResponse get(UUID id);

  Page<ItemResponse> get(int page, int size, ItemQueryParams params);

  ItemResponse create(ItemRequest request);

  ItemResponse update(ItemRequest request, UUID id);

  void delete(UUID id);
}
