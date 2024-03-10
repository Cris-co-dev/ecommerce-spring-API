package com.crisdev.api.storeapi.service.auth;

import com.crisdev.api.storeapi.dto.request.LoginRequest;
import com.crisdev.api.storeapi.dto.request.UserRequest;
import com.crisdev.api.storeapi.dto.response.LoginResponse;
import com.crisdev.api.storeapi.dto.response.UserResponse;
import com.crisdev.api.storeapi.persistence.entity.security.JwtToken;
import com.crisdev.api.storeapi.persistence.entity.security.User;
import com.crisdev.api.storeapi.persistence.repository.security.JwtTokenRepository;
import com.crisdev.api.storeapi.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenService jwtTokenService;
    private final JwtTokenRepository jwtTokenRepository;

    public AuthenticationService(UserService userService,
                                 AuthenticationManager authenticationManager,
                                 JwtTokenService jwtTokenService,
                                 JwtTokenRepository jwtTokenRepository) {

        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.jwtTokenRepository = jwtTokenRepository;
    }

    public UserResponse registerOneCustomer(UserRequest newUser) {

        User user = userService.registerOneCustomer(newUser);
        String jwt = jwtTokenService.generateJwtToken(user, generateExtraClaims(user));
        saveUserToken(user, jwt);

        UserResponse userResponse = entityToResponse(user);
        userResponse.setJwt(jwt);

        return userResponse;
    }

    public LoginResponse login(LoginRequest request) {

        Authentication usernamePassAuthToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        authenticationManager.authenticate(usernamePassAuthToken);

        User user = userService.findByEmail(request.getEmail()).get();
        String jwt = jwtTokenService.generateJwtToken(user, generateExtraClaims(user));
        saveUserToken(user, jwt);

        return new LoginResponse(jwt);
    }

    private void saveUserToken(User user, String jwt) {
        JwtToken jwtToken = new JwtToken();
        jwtToken.setToken(jwt);
        jwtToken.setUser(user);
        jwtToken.setExpiration(jwtTokenService.extractExpiration(jwt));
        jwtToken.setValid(true);

        jwtTokenRepository.save(jwtToken);
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
