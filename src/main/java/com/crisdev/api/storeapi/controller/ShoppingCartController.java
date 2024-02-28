package com.crisdev.api.storeapi.controller;

import com.crisdev.api.storeapi.dto.request.ChangeQuantityRequest;
import com.crisdev.api.storeapi.dto.request.DeleteItemRequest;
import com.crisdev.api.storeapi.dto.request.ShoppingCartItemRequest;
import com.crisdev.api.storeapi.dto.request.ShoppingCartRequest;
import com.crisdev.api.storeapi.dto.response.ShoppingCartItemResponse;
import com.crisdev.api.storeapi.dto.response.ShoppingCartResponse;
import com.crisdev.api.storeapi.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public ResponseEntity<ShoppingCartResponse> readCart(@RequestBody ShoppingCartRequest shoppingCartRequest) {

        ShoppingCartResponse shoppingCart = shoppingCartService.readCart(shoppingCartRequest);
        return ResponseEntity.ok(shoppingCart);

    }

    @PostMapping("/item")
    public ResponseEntity<ShoppingCartItemResponse> addProduct(@RequestBody ShoppingCartItemRequest shoppingCartRequest) {

        ShoppingCartItemResponse response = shoppingCartService.addProductToCart(shoppingCartRequest);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/item/{productItemId}")
    public ResponseEntity<ShoppingCartItemResponse> updateProductQuantity(@PathVariable Long productItemId,
                                                                          @RequestBody ChangeQuantityRequest changeQuantityRequest) {

        ShoppingCartItemResponse response = shoppingCartService.updateProductQuantity(productItemId, changeQuantityRequest);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/item")
    public ResponseEntity<ShoppingCartItemResponse> deleteProductItem(@RequestBody DeleteItemRequest deleteItemRequest) {

        ShoppingCartItemResponse response = shoppingCartService.deleteProductItem(deleteItemRequest);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, String>> clearCart(@PathVariable Long userId) {

        shoppingCartService.clearCart(userId);

        return ResponseEntity.ok(Collections.singletonMap("message", "Cart clear"));
    }


}
