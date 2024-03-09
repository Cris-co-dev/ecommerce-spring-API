package com.crisdev.api.storeapi.controller;

import com.crisdev.api.storeapi.dto.request.ShopOrderRequest;
import com.crisdev.api.storeapi.dto.response.ShopOrderResponse;
import com.crisdev.api.storeapi.service.ShopOrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class ShopOrderController {
    private final ShopOrderService shopOrderService;

    public ShopOrderController(ShopOrderService shopOrderService) {
        this.shopOrderService = shopOrderService;
    }

    @GetMapping("/{usderId}")
    public ResponseEntity<Page<ShopOrderResponse>> readAllOrders(@PathVariable Long userId, Pageable pageable){

        Page<ShopOrderResponse> response = shopOrderService.readAllOrders(userId, pageable);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ShopOrderResponse> madeOrder(@RequestBody @Valid ShopOrderRequest shopOrderRequest){

       ShopOrderResponse response = shopOrderService.createOrder(shopOrderRequest);

       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<ShopOrderResponse> updateOrderStatus(@RequestParam Long orderStatusId,
                                                               @PathVariable UUID orderId){

        ShopOrderResponse response = shopOrderService.updateOrderStatus(orderId, orderStatusId);
        return ResponseEntity.ok(response);
    }

}
