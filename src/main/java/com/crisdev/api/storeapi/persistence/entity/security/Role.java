package com.crisdev.api.storeapi.persistence.entity.security;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private Set<GrantedPermission> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<GrantedPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<GrantedPermission> permissions) {
        this.permissions = permissions;
    }


}
