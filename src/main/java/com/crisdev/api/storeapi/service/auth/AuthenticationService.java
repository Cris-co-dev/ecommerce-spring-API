package com.crisdev.api.storeapi.service.auth;

import com.crisdev.api.storeapi.dto.request.UserRequest;
import com.crisdev.api.storeapi.dto.response.UserResponse;
import com.crisdev.api.storeapi.persistence.entity.security.User;
import com.crisdev.api.storeapi.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    private final UserService userService;

    private final JwtTokenService jwtTokenService;

    public AuthenticationService(UserService userService, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }

    public UserResponse registerOneCustomer(UserRequest newUser) {

        User user = userService.registerOneCustomer(newUser);
        String jwt = jwtTokenService.generateJwtToken(user, generateExtraClaims(user));

        UserResponse userResponse = entityToResponse(user);
        userResponse.setJwt(jwt);

        return userResponse;
    }

    private UserResponse entityToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        userResponse.setRole(user.getRole().getName());
        return userResponse;
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole().getName());
        claims.put("authorities", user.getAuthorities());

        return claims;
    }


}
