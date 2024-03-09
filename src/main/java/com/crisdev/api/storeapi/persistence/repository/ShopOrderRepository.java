package com.crisdev.api.storeapi.persistence.repository;

import com.crisdev.api.storeapi.persistence.entity.ShopOrder;
import com.crisdev.api.storeapi.persistence.entity.security.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ShopOrderRepository extends JpaRepository<ShopOrder, Long> {
    @Query("select s from ShopOrder s where s.orderDate = :orderDate")
    Page<ShopOrder> findAllByOrderDate(LocalDateTime orderDate, Pageable pageable);
    Page<ShopOrder> findAllByUser(User user, Pageable pageable);

    ShopOrder findById(UUID orderId);
}
