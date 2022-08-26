package com.artzvrzn.store.catalogue.service;

import com.artzvrzn.store.catalogue.client.api.ClassifierCategoryClient;
import com.artzvrzn.store.catalogue.dao.api.ItemRepository;
import com.artzvrzn.store.catalogue.dao.entity.ItemEntity;
import com.artzvrzn.store.catalogue.dao.specification.ItemQuerySpecification;
import com.artzvrzn.store.catalogue.model.Item;
import com.artzvrzn.store.catalogue.model.ItemQueryParams;
import com.artzvrzn.store.catalogue.service.api.IItemService;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ItemService implements IItemService {

  @Autowired
  private ItemRepository itemRepository;
  @Autowired
  private ClassifierCategoryClient categoryClient;
  @Autowired
  private ModelMapper mapper;

  @Override
  public Item get(UUID id) {
    return mapper.map(getEntityOrThrow(id), Item.class);
  }

  @Override
  public Page<Item> get(int page, int size, ItemQueryParams params) {
    Pageable pageable = PageRequest.of(page, size, params.getOrder().getSort());
    return itemRepository.findAll(new ItemQuerySpecification(params), pageable)
        .map(e -> mapper.map(e, Item.class));
  }

  @Override
  public Item create(Item dto) {
    validateDtoValues(dto);
    dto.setId(UUID.randomUUID());
    dto.setCreated(LocalDateTime.now(ZoneOffset.UTC));
    dto.setUpdated(dto.getCreated());
    itemRepository.save(mapper.map(dto, ItemEntity.class));
    return dto;
  }

  @Override
  public Item update(Item dto, UUID id, LocalDateTime updated) {
    ItemEntity entity = getEntityOrThrow(id);
    validateDtoValues(dto);
    optimisticLockCheck(entity, updated);
    dto.setUpdated(LocalDateTime.now(ZoneOffset.UTC));
    entity.setUpdated(dto.getUpdated());
    entity.setName(dto.getName());
    entity.setDescription(dto.getDescription());
    entity.setBrand(dto.getBrand());
    entity.setPrice(dto.getPrice());
    entity.setCategory(dto.getCategory());
    entity.setImage(dto.getImage());
    itemRepository.save(entity);
    return dto;
  }

  @Override
  public void delete(UUID id, LocalDateTime updated) {
    ItemEntity entity = getEntityOrThrow(id);
    optimisticLockCheck(entity, updated);
    itemRepository.delete(entity);
  }

  private boolean isCategoryExist(UUID id) {
    return categoryClient.headForHeaders(id).getStatusCode().equals(HttpStatus.OK);
  }

  private ItemEntity getEntityOrThrow(UUID id) {
    Optional<ItemEntity> optionalEntity = itemRepository.findById(id);
    if (optionalEntity.isEmpty()) {
      throw new IllegalArgumentException("doesn't exist");
    }
    return optionalEntity.get();
  }

  private void validateDtoValues(Item dto) {
    if (dto == null) {
      throw new IllegalArgumentException("dto is null");
    }
    if (!isCategoryExist(dto.getCategory())) {
      throw new IllegalArgumentException("category not exist");
    }
  }

  private void optimisticLockCheck(ItemEntity entity, LocalDateTime updated) {
    if (!entity.getUpdated().isEqual(updated)) {
      throw new IllegalStateException("already updated");
    }
  }
}
