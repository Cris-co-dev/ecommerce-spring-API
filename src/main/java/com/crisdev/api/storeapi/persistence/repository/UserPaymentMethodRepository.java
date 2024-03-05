package com.crisdev.api.storeapi.persistence.repository;

import com.crisdev.api.storeapi.persistence.entity.UserPaymentMethod;
import com.crisdev.api.storeapi.persistence.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPaymentMethodRepository extends JpaRepository<UserPaymentMethod, Long> {
    List<UserPaymentMethod> findAllByUser(User user);

}
