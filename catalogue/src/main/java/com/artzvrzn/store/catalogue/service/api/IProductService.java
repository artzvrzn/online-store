package com.artzvrzn.store.catalogue.service.api;

import com.artzvrzn.store.catalogue.model.Product;
import com.artzvrzn.store.catalogue.model.ProductQueryParams;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface IProductService {

  Product get(UUID id);

  Page<Product> get(int page, int size, ProductQueryParams params);

  Product create(Product dto);

  Product update(Product dto, UUID id, LocalDateTime updated);

  void incrementRating(UUID id);

  void decrementRating(UUID id);

  void delete(UUID id, LocalDateTime updated);
}
