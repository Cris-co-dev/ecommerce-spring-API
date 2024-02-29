package com.crisdev.api.storeapi.controller;

import com.crisdev.api.storeapi.dto.response.AddressResponse;
import com.crisdev.api.storeapi.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AddressService addressService;

    public UserController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{userId}/addresses")
    public ResponseEntity<List<AddressResponse>> readAllUserAddresses(@PathVariable Long userId) {
        return ResponseEntity.ok(addressService.readAll(userId));
    }

}
