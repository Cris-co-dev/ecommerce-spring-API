package com.crisdev.api.storeapi.persistence.entity;

import com.crisdev.api.storeapi.persistence.entity.security.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "shoppingCart")
    private Set<ShoppingCartItem> shoppingCartItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void setShoppingCartItems(Set<ShoppingCartItem> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }
}
