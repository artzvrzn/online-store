package com.artzvrzn.store.classifier.service.api;

import com.artzvrzn.store.classifier.model.Category;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface ICategoryService {

  Category get(UUID id);

  List<Category> getAll();

  List<Category> getDirectSubcategories(UUID parentId);

  List<Category> getIndirectSubcategories(UUID parentId);

  Category create(Category dto);

  Category createSubcategory(Category dto, UUID parentId);

  Category update(Category dto, UUID id, LocalDateTime updated);

  void delete(UUID id, LocalDateTime updated);
}
