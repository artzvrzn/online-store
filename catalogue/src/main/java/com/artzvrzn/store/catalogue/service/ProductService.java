package com.artzvrzn.store.catalogue.service;

import com.artzvrzn.store.catalogue.client.api.ClassifierCategoryClient;
import com.artzvrzn.store.catalogue.client.api.ClassifierCurrencyClient;
import com.artzvrzn.store.catalogue.dao.api.ProductRepository;
import com.artzvrzn.store.catalogue.dao.entity.ProductEntity;
import com.artzvrzn.store.catalogue.dao.specification.ProductQuerySpecification;
import com.artzvrzn.store.catalogue.model.Product;
import com.artzvrzn.store.catalogue.model.ProductQueryParams;
import com.artzvrzn.store.catalogue.service.api.IProductService;
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
public class ProductService implements IProductService {

  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private ClassifierCategoryClient categoryClient;
  @Autowired
  private ModelMapper mapper;

  @Override
  public Product get(UUID id) {
    return null;
  }

  @Override
  public Page<Product> get(int page, int size, ProductQueryParams params) {
    Pageable pageable = PageRequest.of(page, size, params.getOrder().getSort());
    return productRepository.findAll(new ProductQuerySpecification(params), pageable)
        .map(e -> mapper.map(e, Product.class));
  }

  @Override
  public Product create(Product dto) {
    validateDtoValues(dto);
    dto.setId(UUID.randomUUID());
    dto.setCreated(LocalDateTime.now(ZoneOffset.UTC));
    dto.setUpdated(dto.getCreated());
    productRepository.save(mapper.map(dto, ProductEntity.class));
    return dto;
  }

  @Override
  public Product update(Product dto, UUID id, LocalDateTime updated) {
    ProductEntity entity = getEntityOrThrow(id);
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
    entity.setRating(dto.getRating());
    productRepository.save(entity);
    return dto;
  }

  @Override
  public void incrementRating(UUID id) {
    productRepository.incrementRating(id);
  }

  @Override
  public void decrementRating(UUID id) {
    productRepository.decrementRating(id);
  }

  @Override
  public void delete(UUID id, LocalDateTime updated) {
    ProductEntity entity = getEntityOrThrow(id);
    optimisticLockCheck(entity, updated);
    productRepository.delete(entity);
  }

  private boolean isCategoryExist(UUID id) {
    return categoryClient.headForHeaders(id).getStatusCode().equals(HttpStatus.OK);
  }

  private ProductEntity getEntityOrThrow(UUID id) {
    Optional<ProductEntity> optionalEntity = productRepository.findById(id);
    if (optionalEntity.isEmpty()) {
      throw new IllegalArgumentException("doesn't exist");
    }
    return optionalEntity.get();
  }

  private void validateDtoValues(Product dto) {
    if (dto == null) {
      throw new IllegalArgumentException("dto is null");
    }
    if (!isCategoryExist(dto.getCategory())) {
      throw new IllegalArgumentException("category not exist");
    }
  }

  private void optimisticLockCheck(ProductEntity entity, LocalDateTime updated) {
    if (!entity.getUpdated().isEqual(updated)) {
      throw new IllegalStateException("already updated");
    }
  }
}
