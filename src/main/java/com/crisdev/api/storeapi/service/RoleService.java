package com.crisdev.api.storeapi.service;

import com.crisdev.api.storeapi.persistence.entity.security.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findDefaultRole();

}
