package com.crisdev.api.storeapi.persistence.repository;

import com.crisdev.api.storeapi.persistence.entity.ShoppingCart;
import com.crisdev.api.storeapi.persistence.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByUser(User user);


}
