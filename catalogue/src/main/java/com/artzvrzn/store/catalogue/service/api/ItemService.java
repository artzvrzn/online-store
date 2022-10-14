package com.artzvrzn.store.catalogue.service.api;

import com.artzvrzn.store.catalogue.dto.CategoryDto;
import com.artzvrzn.store.catalogue.dto.ItemDto;
import com.artzvrzn.store.catalogue.domain.ItemQueryParams;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface ItemService extends CRUDService<ItemDto, UUID> {

  List<ItemDto> getAll();

  Page<ItemDto> getPage(int page, int size, ItemQueryParams params);
}
