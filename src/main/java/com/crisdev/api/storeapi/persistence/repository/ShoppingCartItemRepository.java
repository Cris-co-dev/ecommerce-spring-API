package com.crisdev.api.storeapi.persistence.repository;

import com.crisdev.api.storeapi.persistence.entity.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {


}
