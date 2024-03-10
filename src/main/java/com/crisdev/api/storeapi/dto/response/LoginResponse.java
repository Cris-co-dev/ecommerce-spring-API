package com.crisdev.api.storeapi.dto.response;

import java.io.Serializable;

public class LoginResponse implements Serializable{

    private String jwt;

    public LoginResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
