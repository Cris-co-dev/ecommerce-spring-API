package com.crisdev.api.storeapi.persistence.entity.util;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserAddressPK implements Serializable {

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "address_id")
    private Long addressId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
