package com.crisdev.api.storeapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class UserRequest implements Serializable {
    @Size(min = 4)
    private String username;
    @Email
    private String email;
    @Size(min = 8)
    private String password;
    @Size(min = 8)
    private String repeatedPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }
}
