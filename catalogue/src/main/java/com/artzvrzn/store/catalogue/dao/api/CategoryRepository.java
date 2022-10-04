package com.artzvrzn.store.catalogue.dao.api;

import com.artzvrzn.store.catalogue.domain.Category;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

  List<Category> findAllByParentCategory_Id(UUID parentId, Sort sort);

  boolean existsByName(String name);
}
