package com.crisdev.api.storeapi.persistence.repository.security;

import com.crisdev.api.storeapi.persistence.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
