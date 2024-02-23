package com.crisdev.api.storeapi.persistence.entity;

import com.crisdev.api.storeapi.persistence.entity.util.CategoryStatusEnum;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "product_category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category_name")
    private String name;
    @Enumerated(EnumType.STRING)
    private CategoryStatusEnum status;

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

    public CategoryStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CategoryStatusEnum status) {
        this.status = status;
    }
}
