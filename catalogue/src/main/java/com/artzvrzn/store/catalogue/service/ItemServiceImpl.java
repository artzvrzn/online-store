package com.artzvrzn.store.catalogue.service;

import static com.artzvrzn.store.catalogue.domain.constant.CommonMessage.*;

import com.artzvrzn.store.catalogue.dao.api.CategoryRepository;
import com.artzvrzn.store.catalogue.dao.api.ItemRepository;
import com.artzvrzn.store.catalogue.dao.specification.ItemQuerySpecification;
import com.artzvrzn.store.catalogue.domain.Category;
import com.artzvrzn.store.catalogue.domain.Item;
import com.artzvrzn.store.catalogue.dto.ItemDto;
import com.artzvrzn.store.catalogue.domain.ItemQueryParams;
import com.artzvrzn.store.catalogue.mapper.ItemMapper;
import com.artzvrzn.store.catalogue.service.api.ItemService;
import com.artzvrzn.store.catalogue.service.util.CrudUtils;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
  private final ItemMapper mapper;

  @Override
  public ItemDto get(UUID id) {
    return mapper.toDto(CrudUtils.getEntityOrThrow(id, itemRepository));
  }

  @Override
  public List<ItemDto> getAll() {
    return itemRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Page<ItemDto> getPage(int page, int size, ItemQueryParams params) {
    Pageable pageable = PageRequest.of(page, size, params.getOrder().getSort());
    return itemRepository.findAll(new ItemQuerySpecification(params), pageable)
      .map(mapper::toDto);
  }

  @Override
  public ItemDto create(ItemDto request) {
    Item item = mapper.toEntity(request);
    item = itemRepository.save(item);
    log.debug(LOG_CREATED.getMessage(), ENTITY_NAME, item.getId());
    return mapper.toDto(item);
  }

  @Override
  public ItemDto update(ItemDto request, UUID id) {
    Item item = CrudUtils.getEntityOrThrow(id, itemRepository);
    item = mapper.toEntity(item, request);
    item = itemRepository.save(item);
    log.debug(LOG_UPDATED.getMessage(), ENTITY_NAME, item.getId());
    return mapper.toDto(item);
  }

  @Override
  public void delete(UUID id) {
    itemRepository.deleteById(id);
    log.debug(LOG_DELETED.getMessage(), ENTITY_NAME, id);
  }
}
