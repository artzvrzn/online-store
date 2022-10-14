package com.artzvrzn.store.catalogue.dao.specification;

import com.artzvrzn.store.catalogue.domain.Item;
import com.artzvrzn.store.catalogue.domain.ItemQueryParams;
import com.artzvrzn.store.catalogue.domain.Rating;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class ItemQuerySpecification implements Specification<Item> {
  private ItemQueryParams params;

  @Override
  public Predicate toPredicate(
      Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
      String brand, Root<Item> root, CriteriaBuilder cb) {
    final String field = "brand";
    if (brand.startsWith("!")) {
      return cb.notEqual(cb.upper(root.get(field)), brand.substring(1).toUpperCase());
    } else {
      return cb.equal(cb.upper(root.get(field)), brand.toUpperCase());
    }
  }

  private static Predicate getBrandsPredicate(
      ItemQueryParams params, Root<Item> root, CriteriaBuilder cb) {
    return cb.and(
        params.getBrand()
            .stream()
            .map(b -> getBrandPredicate(b, root, cb))
            .toArray(Predicate[]::new));
  }

  private static Predicate getCategoryPredicate(
      ItemQueryParams params, Root<Item> root, CriteriaBuilder cb) {
    return cb.equal(root.get("category"), params.getCategory());
  }

  private static Predicate getMinPricePredicate(
      ItemQueryParams params, Root<Item> root, CriteriaBuilder cb) {
    return cb.greaterThanOrEqualTo(root.get("price"), params.getMinPrice());
  }

  private static Predicate getMaxPricePredicate(
      ItemQueryParams params, Root<Item> root, CriteriaBuilder cb) {
    return cb.lessThanOrEqualTo(root.get("price"), params.getMaxPrice());
  }
}
