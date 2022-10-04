package com.artzvrzn.store.catalogue.service.api;

import com.artzvrzn.store.catalogue.domain.Category;
import com.artzvrzn.store.catalogue.dto.request.CategoryRequest;
import com.artzvrzn.store.catalogue.dto.response.CategoryResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CategoryService {

  CategoryResponse get(UUID id);

  List<CategoryResponse> getAll();

  List<CategoryResponse> getTopLevelCategories();

  List<CategoryResponse> getDirectSubcategories(UUID parentId);

  List<CategoryResponse> getIndirectSubcategories(UUID parentId);

  boolean isExist(UUID id);

  CategoryResponse create(CategoryRequest request);

  CategoryResponse update(CategoryRequest request, UUID id);

  void delete(UUID id);
}
