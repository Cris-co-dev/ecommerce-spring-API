package com.crisdev.api.storeapi.dto.response;

import com.crisdev.api.storeapi.persistence.entity.PaymentType;

import java.io.Serializable;
import java.util.Date;

public class UserPaymentMethodResponse implements Serializable {

    private Long id;
    private PaymentType paymentType;
    private String provider;
    private Date expiryDate;
    private boolean isDefault;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
