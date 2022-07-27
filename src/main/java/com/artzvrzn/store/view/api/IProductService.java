package com.artzvrzn.store.view.api;

import com.artzvrzn.store.model.Product;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface IProductService {

  Product get(UUID id);

  Page<Product> get(int page, int size);

  Product create(Product product);

  Product update(UUID id, LocalDateTime updated, Product product);

  void delete(UUID id, LocalDateTime updated);
}
