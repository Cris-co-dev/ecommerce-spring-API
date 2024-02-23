package com.crisdev.api.storeapi.persistence.entity;

import com.crisdev.api.storeapi.persistence.entity.util.ShippingMethodTypeEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "shipping_method")
public class ShippingMethod implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private ShippingMethodTypeEnum shippingMethodTypeEnum;

    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShippingMethodTypeEnum getShippingMethodTypeEnum() {
        return shippingMethodTypeEnum;
    }

    public void setShippingMethodTypeEnum(ShippingMethodTypeEnum shippingMethodTypeEnum) {
        this.shippingMethodTypeEnum = shippingMethodTypeEnum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
