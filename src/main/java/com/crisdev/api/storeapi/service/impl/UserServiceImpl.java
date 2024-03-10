package com.crisdev.api.storeapi.service.impl;

import com.crisdev.api.storeapi.dto.request.UserRequest;
import com.crisdev.api.storeapi.exception.InvalidPasswordException;
import com.crisdev.api.storeapi.exception.ObjectNotFoundException;
import com.crisdev.api.storeapi.persistence.entity.ShoppingCart;
import com.crisdev.api.storeapi.persistence.entity.security.Role;
import com.crisdev.api.storeapi.persistence.entity.security.User;
import com.crisdev.api.storeapi.persistence.repository.ShoppingCartRepository;
import com.crisdev.api.storeapi.persistence.repository.security.UserRepository;
import com.crisdev.api.storeapi.service.RoleService;
import com.crisdev.api.storeapi.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ShoppingCartRepository shoppingCartRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.shoppingCartRepository = shoppingCartRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User registerOneCustomer(UserRequest newUser) {

        validatePassword(newUser);

        Role defaultRole = roleService.findDefaultRole()
                .orElseThrow(() -> new ObjectNotFoundException("Role not found. Default role"));

        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRole(defaultRole);

        User save = userRepository.save(user);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(save);
        shoppingCartRepository.save(shoppingCart);

        return save;
    }

    private void validatePassword(UserRequest newUser) {
        if (!StringUtils.hasText(newUser.getPassword()) || !StringUtils.hasText(newUser.getRepeatedPassword())){
            throw new InvalidPasswordException("Passwords don't match");
        }

        if (!newUser.getPassword().equals(newUser.getRepeatedPassword())){
            throw new InvalidPasswordException("Passwords don't match");
        }
    }
}
