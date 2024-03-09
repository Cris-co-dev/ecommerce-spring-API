package com.crisdev.api.storeapi.service;

import com.crisdev.api.storeapi.dto.request.ChangeQuantityRequest;
import com.crisdev.api.storeapi.dto.request.DeleteItemRequest;
import com.crisdev.api.storeapi.dto.request.ShoppingCartItemRequest;
import com.crisdev.api.storeapi.dto.request.ShoppingCartRequest;
import com.crisdev.api.storeapi.dto.response.ShoppingCartItemResponse;
import com.crisdev.api.storeapi.dto.response.ShoppingCartResponse;

public interface ShoppingCartService {
    ShoppingCartItemResponse addProductToCart(ShoppingCartItemRequest userId);
    ShoppingCartResponse readCart(ShoppingCartRequest shoppingCartRequest);
    ShoppingCartItemResponse updateProductQuantity(Long productItemId, ChangeQuantityRequest changeQuantityRequest);
    ShoppingCartItemResponse deleteProductItem(DeleteItemRequest deleteItemRequest);
    void clearCart(Long userId);
}
