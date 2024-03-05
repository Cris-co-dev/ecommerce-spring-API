package com.crisdev.api.storeapi.dto.request;

import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public class UserPaymentMethodRequest implements Serializable {

    private Long userId;
    private Long paymentTypeId;
    private String provider;
    private String accountNumber;
    @Pattern(regexp = "^\\d{2}/\\d{2}$")
    private String date;
    private boolean isDefault;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
