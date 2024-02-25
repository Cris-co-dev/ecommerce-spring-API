package com.crisdev.api.storeapi.persistence.entity.util;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PromotionCategoryPK implements Serializable {

    private Long categoryId;

    private Long promotionId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }
}
