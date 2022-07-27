package com.artzvrzn.store.view;

import com.artzvrzn.store.dao.api.ProductRepository;
import com.artzvrzn.store.dao.entity.ProductEntity;
import com.artzvrzn.store.model.Product;
import com.artzvrzn.store.view.api.CRUDService;
import com.artzvrzn.store.view.api.IProductService;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends CRUDService<ProductEntity> implements IProductService {

  protected ProductService(ConversionService conversionService, ProductRepository repository) {
    super(conversionService, repository);
  }

  @Override
  public Product get(UUID id) {
    ProductEntity entity = getEntityOrThrow(id);
    return convertOrThrow(entity, Product.class);
  }

  @Override
  public Page<Product> get(int page, int size) {
    Pageable request = PageRequest.of(page, size, Sort.by("created").descending());
    return repository.findAll(request).map(e -> conversionService.convert(e, Product.class));
  }

  @Override
  public Product create(Product product) {
    product = assignIdAndTime(product);
    ProductEntity entity = convertOrThrow(product, ProductEntity.class);
    repository.save(entity);
    return product;
  }

  @Override
  public Product update(UUID id, LocalDateTime updated, Product product) {
    ProductEntity entity = getEntityOrThrow(id, updated); // checks if exist //TODO write own sql
    product.setId(entity.getId());
    product.setCreated(entity.getCreated());
    product.setUpdated(LocalDateTime.now(ZoneOffset.UTC));
    repository.save(convertOrThrow(product, ProductEntity.class));
    return product;
  }

  @Override
  public void delete(UUID id, LocalDateTime updated) {
    ProductEntity entity = getEntityOrThrow(id, updated); //TODO also
    repository.delete(entity);
  }
}
