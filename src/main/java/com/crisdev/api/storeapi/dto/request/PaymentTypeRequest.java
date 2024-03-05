package com.crisdev.api.storeapi.dto.request;

import java.io.Serializable;

public class PaymentTypeRequest implements Serializable {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
