package com.crisdev.api.storeapi.dto.response;

import java.io.Serializable;

public class ShoppingCartItemResponse implements Serializable {
    private String message;
    private Long productItemId;
    private Integer quantity;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(Long productItemId) {
        this.productItemId = productItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
