package com.crisdev.api.storeapi.dto.request;

import java.io.Serializable;

public class ShoppingCartRequest implements Serializable {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
