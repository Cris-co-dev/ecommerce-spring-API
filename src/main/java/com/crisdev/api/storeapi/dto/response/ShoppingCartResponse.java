package com.crisdev.api.storeapi.dto.response;

import com.crisdev.api.storeapi.dto.response.util.CartItem;

import java.io.Serializable;
import java.util.List;

public class ShoppingCartResponse implements Serializable {

    private List<CartItem> cartItems;

    public ShoppingCartResponse(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
