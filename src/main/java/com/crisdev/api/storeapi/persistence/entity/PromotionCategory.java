package com.crisdev.api.storeapi.persistence.entity;

import com.crisdev.api.storeapi.persistence.entity.util.PromotionCategoryPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "promotion_category")
public class PromotionCategory {
    @EmbeddedId
    private PromotionCategoryPK id;

    public PromotionCategoryPK getId() {
        return id;
    }

    public void setId(PromotionCategoryPK id) {
        this.id = id;
    }
}
