package com.gmail.gak.artem.backend.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Role extends AbstractEntity {

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
