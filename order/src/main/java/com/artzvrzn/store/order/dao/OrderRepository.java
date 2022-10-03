package com.artzvrzn.store.order.dao;

import com.artzvrzn.store.order.domain.Order;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

  Page<Order> findAllByUserInfo_UserId(UUID userId, Pageable pageable);
}

