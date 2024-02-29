package com.crisdev.api.storeapi.dto.request;

import java.io.Serializable;

public class CountryRequest implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
