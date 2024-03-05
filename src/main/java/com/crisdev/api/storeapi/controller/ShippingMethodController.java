package com.crisdev.api.storeapi.controller;

import com.crisdev.api.storeapi.dto.request.ShippingMethodRequest;
import com.crisdev.api.storeapi.persistence.entity.ShippingMethod;
import com.crisdev.api.storeapi.service.ShippingMethodService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipping-methods")
public class ShippingMethodController {

    private final ShippingMethodService shippingMethodService;

    public ShippingMethodController(ShippingMethodService shippingMethodService) {
        this.shippingMethodService = shippingMethodService;
    }

    @GetMapping
    public ResponseEntity<List<ShippingMethod>> readAllShippingMethods() {

        List<ShippingMethod> methods = shippingMethodService.readAllShippingMethods();

        return methods.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(methods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingMethod> readOneShippingMethods(@PathVariable Long id) {

        ShippingMethod methods = shippingMethodService.readOneShippingMethods(id);

        return ResponseEntity.ok(methods);
    }

    @PostMapping
    public ResponseEntity<ShippingMethod> addShippingMethod(@RequestBody @Valid ShippingMethodRequest shippingMethodRequest) {

        ShippingMethod shippingMethod = shippingMethodService.addShippingMethod(shippingMethodRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(shippingMethod);
    }


}
