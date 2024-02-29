package com.crisdev.api.storeapi.controller;

import com.crisdev.api.storeapi.dto.request.AddressRequest;
import com.crisdev.api.storeapi.dto.response.AddressResponse;
import com.crisdev.api.storeapi.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;


    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/address/{addressId}")
    public ResponseEntity<AddressResponse> readAddressById(@PathVariable Long addressId) {
        return ResponseEntity.ok(addressService.findById(addressId));
    }

    @PostMapping
    public ResponseEntity<AddressResponse> createAddress(@RequestBody AddressRequest addressRequest) {

        AddressResponse newAddress = addressService.createNewAddress(addressRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(newAddress);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long addressId,
                                                         @RequestBody AddressRequest addressRequest) {

        AddressResponse addressResponse = addressService.updateAddress(addressId, addressRequest);
        return ResponseEntity.ok(addressResponse);
    }

    @PatchMapping("/{userId}/{addressId}")
    public ResponseEntity<AddressResponse> updateDefaultAddress(@PathVariable Long userId,
                                                         @PathVariable Long addressId) {
        AddressResponse addressResponse = addressService.updateDefaultAddress(userId,addressId);
        return ResponseEntity.ok(addressResponse);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Map<String,String>> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.ok(Collections.singletonMap("message", "Address deleted successfully"));
    }

}
