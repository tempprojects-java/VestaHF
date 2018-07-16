package com.gmail.gak.artem.ui.data;

import com.gmail.gak.artem.backend.data.entity.Role;

public class UserFilter {
    private String name;
    private Role role;

    public UserFilter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
