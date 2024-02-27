package com.crisdev.api.storeapi.persistence.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String name;
    private String description;
    private BigDecimal basePrice;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @Column(name = "default_image")
    private String defaultProductImageUrl;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<ProductItem> productItems;
    public enum ProductStatus {
        ENABLED, DISABLED
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal base_price) {
        this.basePrice = base_price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getDefaultProductImageUrl() {
        return defaultProductImageUrl;
    }

    public void setDefaultProductImageUrl(String defaultProductImageurl) {
        this.defaultProductImageUrl = defaultProductImageurl;
    }

    public Set<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(Set<ProductItem> productItems) {
        this.productItems = productItems;
    }
}
