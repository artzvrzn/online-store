package com.artzvrzn.store.catalogue.dao.specification;

import com.artzvrzn.store.catalogue.dao.entity.ItemEntity;
import com.artzvrzn.store.catalogue.model.ItemQueryParams;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class ItemQuerySpecification implements Specification<ItemEntity> {

  private ItemQueryParams params;

  @Override
  public Predicate toPredicate(
      Root<ItemEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    List<Predicate> predicates = new ArrayList<>();
    if (params.getBrand() != null && !params.getBrand().isEmpty()) {
      predicates.add(getBrandsPredicate(params, root, cb));
    }
    if (params.getCategory() != null) {
      predicates.add(getCategoryPredicate(params, root, cb));
    }
    if (params.getMinPrice() != null) {
      predicates.add(getMinPricePredicate(params, root, cb));
    }
    if (params.getMaxPrice() != null) {
      predicates.add(getMaxPricePredicate(params, root, cb));
    }
    return predicates.isEmpty() ? null : cb.and(predicates.toArray(Predicate[]::new));
  }

  private static Predicate getBrandPredicate(
      String brand, Root<ItemEntity> root, CriteriaBuilder cb) {
    final String field = "brand";
    if (brand.startsWith("!")) {
      return cb.notEqual(cb.upper(root.get(field)), brand.substring(1).toUpperCase());
    } else {
      return cb.equal(cb.upper(root.get(field)), brand.toUpperCase());
    }
  }

  private static Predicate getBrandsPredicate(
      ItemQueryParams params, Root<ItemEntity> root, CriteriaBuilder cb) {
    return cb.and(
        params.getBrand()
            .stream()
            .map(b -> getBrandPredicate(b, root, cb))
            .toArray(Predicate[]::new));
  }

  private static Predicate getCategoryPredicate(
      ItemQueryParams params, Root<ItemEntity> root, CriteriaBuilder cb) {
    return cb.equal(root.get("category"), params.getCategory());
  }

  private static Predicate getMinPricePredicate(
      ItemQueryParams params, Root<ItemEntity> root, CriteriaBuilder cb) {
    return cb.greaterThanOrEqualTo(root.get("price"), params.getMinPrice());
  }

  private static Predicate getMaxPricePredicate(
      ItemQueryParams params, Root<ItemEntity> root, CriteriaBuilder cb) {
    return cb.lessThanOrEqualTo(root.get("price"), params.getMaxPrice());
  }
}
