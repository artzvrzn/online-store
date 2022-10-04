package com.artzvrzn.store.catalogue.service;

import static com.artzvrzn.store.catalogue.domain.constant.CommonMessage.LOG_CREATED;

import com.artzvrzn.store.catalogue.dao.api.CategoryRepository;
import com.artzvrzn.store.catalogue.dao.api.ItemRepository;
import com.artzvrzn.store.catalogue.dao.specification.ItemQuerySpecification;
import com.artzvrzn.store.catalogue.domain.Category;
import com.artzvrzn.store.catalogue.domain.Item;
import com.artzvrzn.store.catalogue.domain.Stock;
import com.artzvrzn.store.catalogue.dto.request.ItemRequest;
import com.artzvrzn.store.catalogue.dto.response.ItemResponse;
import com.artzvrzn.store.catalogue.domain.ItemQueryParams;
import com.artzvrzn.store.catalogue.service.api.ItemService;
import com.artzvrzn.store.catalogue.service.util.CrudUtils;
import java.util.Random;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
  private static final String ENTITY_NAME = "item";
  private final ItemRepository itemRepository;
  private final CategoryRepository categoryRepository;
  private final ModelMapper mapper;

  @Override
  public ItemResponse get(UUID id) {
    return mapper.map(CrudUtils.getEntityOrThrow(id, itemRepository), ItemResponse.class);
  }

  @Override
  public Page<ItemResponse> get(int page, int size, ItemQueryParams params) {
    Pageable pageable = PageRequest.of(page, size);
    return itemRepository.findAll(new ItemQuerySpecification(params), pageable)
      .map(e -> mapper.map(e, ItemResponse.class));
  }

  @Override
  public ItemResponse create(ItemRequest request) {
    Category category = categoryRepository.getReferenceById(request.getCategory());
    Item item = Item.getBuilder()
      .withName(request.getName())
      .withDescription(request.getDescription())
      .withBrand(request.getBrand())
      .withImage(request.getImage())
      .withPrice(request.getPrice())
      .withCategory(category)
      .build();
    item.setStock(new Stock(item));
    item = itemRepository.save(item);
    log.debug(LOG_CREATED.toString(), ENTITY_NAME, item.getId());
    return mapper.map(item, ItemResponse.class);
  }

  @Override
  public ItemResponse update(ItemRequest request, UUID id) {
    Item item = CrudUtils.getEntityOrThrow(id, itemRepository);

    return null;
  }

  @Override
  public void delete(UUID id) {
    itemRepository.deleteById(id);
  }
}
