package com.artzvrzn.store.view.api;

import com.artzvrzn.store.model.Category;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface ICategoryService {

  Category get(UUID id);

  Page<Category> get(int page, int size);

  Category create(Category category);

  Category update(UUID id, LocalDateTime updated, Category category);

  void delete(UUID id, LocalDateTime updated);
}
