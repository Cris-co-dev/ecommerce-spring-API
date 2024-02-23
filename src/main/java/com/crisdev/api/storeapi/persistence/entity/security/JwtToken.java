package com.crisdev.api.storeapi.persistence.entity.security;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class JwtToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expiration;
    private boolean isValid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
