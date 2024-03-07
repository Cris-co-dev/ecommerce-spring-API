package com.crisdev.api.storeapi.dto.request;

import java.io.Serializable;

public class ChangeQuantityRequest implements Serializable {

    private Long userId;
    private Integer quantity;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
