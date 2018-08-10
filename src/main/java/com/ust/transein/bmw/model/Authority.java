package com.ust.transein.bmw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    Long id;
    UserRoleName name;


    @Override
    public String getAuthority() {
        return name.name();
    }

    public void setName(UserRoleName name) {
        this.name = name;
    }

    @JsonIgnore
    public UserRoleName getName() {
        return name;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
