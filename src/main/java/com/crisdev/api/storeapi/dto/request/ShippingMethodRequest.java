package com.crisdev.api.storeapi.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShippingMethodRequest implements Serializable {
    private String name;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
