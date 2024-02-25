package com.crisdev.api.storeapi.persistence.entity;

import com.crisdev.api.storeapi.persistence.entity.util.UserAddressPK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_address")
public class UserAddress {
    @EmbeddedId
    private UserAddressPK userAddressPK;

    private boolean isDefault;

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
