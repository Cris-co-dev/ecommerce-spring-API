package com.crisdev.api.storeapi.dto.request;

import java.io.Serializable;

public class ShoppingCartItemRequest implements Serializable {

    private Long userId;
    private Long productItemId;
    private Integer quantity;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
