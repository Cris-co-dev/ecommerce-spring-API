package com.crisdev.api.storeapi.dto.response;

import com.crisdev.api.storeapi.persistence.entity.Category;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public class ProductResponse implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Category category;
    private String defaultProductImageUrl;
    private String status;
    private BigDecimal basePrice;
    private Set<ProductItemResponse> productItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDefaultProductImageUrl() {
        return defaultProductImageUrl;
    }

    public void setDefaultProductImageUrl(String defaultProductImageUrl) {
        this.defaultProductImageUrl = defaultProductImageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Set<ProductItemResponse> getProductItems() {
        return productItems;
    }

    public void setProductItems(Set<ProductItemResponse> productItems) {
        this.productItems = productItems;
    }
}
