package com.crisdev.api.storeapi.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public class ProductRequest implements Serializable {

    private String name;
    private String description;
    private Long categoryId;
    private String defaultProductImageUrl;
    private BigDecimal basePrice;
    private Set<ProductItemRequest> productItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDefaultProductImageUrl() {
        return defaultProductImageUrl;
    }

    public void setDefaultProductImageUrl(String defaultProductImageUrl) {
        this.defaultProductImageUrl = defaultProductImageUrl;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Set<ProductItemRequest> getProductItems() {
        return productItems;
    }

    public void setProductItems(Set<ProductItemRequest> productItems) {
        this.productItems = productItems;
    }
}
