package com.crisdev.api.storeapi.controller;

import com.crisdev.api.storeapi.dto.request.LoginRequest;
import com.crisdev.api.storeapi.dto.request.UserRequest;
import com.crisdev.api.storeapi.dto.response.LoginResponse;
import com.crisdev.api.storeapi.dto.response.UserResponse;
import com.crisdev.api.storeapi.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest userRequest) {
        UserResponse userResponse = authenticationService.registerOneCustomer(userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {

        LoginResponse response = authenticationService.login(request);

        return ResponseEntity.ok(response);
    }

}
