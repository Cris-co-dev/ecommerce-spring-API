package com.crisdev.api.storeapi.dto.request;

import java.io.Serializable;

public class ChangeQuantityRequest implements Serializable {

    private Long userId;
    private String quantity;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
