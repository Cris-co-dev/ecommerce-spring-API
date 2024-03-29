package com.crisdev.api.storeapi.service;

import com.crisdev.api.storeapi.dto.request.UserRequest;
import com.crisdev.api.storeapi.persistence.entity.security.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    User registerOneCustomer(UserRequest newUser);
}
