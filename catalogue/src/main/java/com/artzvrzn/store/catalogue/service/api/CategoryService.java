package com.artzvrzn.store.catalogue.service.api;

import com.artzvrzn.store.catalogue.dto.CategoryDto;
import java.util.List;
import java.util.UUID;

public interface CategoryService extends CRUDService<CategoryDto, UUID> {

  List<CategoryDto> getAll();

  List<CategoryDto> getTopLevelCategories();

  List<CategoryDto> getDirectSubcategories(UUID parentId);

  List<CategoryDto> getIndirectSubcategories(UUID parentId);

  boolean isExist(UUID id);
}
