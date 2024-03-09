package com.crisdev.api.storeapi.controller;

import com.crisdev.api.storeapi.dto.response.AddressResponse;
import com.crisdev.api.storeapi.dto.response.UserPaymentMethodResponse;
import com.crisdev.api.storeapi.service.AddressService;
import com.crisdev.api.storeapi.service.UserPaymentMethodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AddressService addressService;
    private final UserPaymentMethodService userPaymentMethodService;

    public UserController(AddressService addressService, UserPaymentMethodService userPaymentMethodService) {
        this.addressService = addressService;
        this.userPaymentMethodService = userPaymentMethodService;
    }

    @GetMapping("/{userId}/addresses")
    public ResponseEntity<List<AddressResponse>> readAllUserAddresses(@PathVariable Long userId) {
        return ResponseEntity.ok(addressService.readAll(userId));
    }
    @GetMapping("/{userId}/payment-methods")
    public ResponseEntity<List<UserPaymentMethodResponse>> readAllPaymentMethods(@PathVariable Long userId) {
        return ResponseEntity.ok(userPaymentMethodService.readAll(userId));
    }

}
