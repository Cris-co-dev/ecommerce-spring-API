package com.crisdev.api.storeapi.dto.request;

import java.io.Serializable;

public class ShopOrderRequest implements Serializable {
    private Long userId;
    private Long paymentMethodId;
    private Long shippingMethodId;
    private Long shippingAddressId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Long getShippingMethodId() {
        return shippingMethodId;
    }

    public void setShippingMethodId(Long shippingMethodId) {
        this.shippingMethodId = shippingMethodId;
    }

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }
}
