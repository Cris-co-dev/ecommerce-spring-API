package com.crisdev.api.storeapi.dto.request;

public class DeleteItemRequest {

    private Long userId;
    private Long productItemId;

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
}
