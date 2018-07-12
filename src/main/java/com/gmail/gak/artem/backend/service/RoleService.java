package com.gmail.gak.artem.backend.service;

import com.gmail.gak.artem.backend.data.entity.Role;
import com.gmail.gak.artem.backend.repository.RoleRepository;

public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void save(Role role) {
        roleRepository.save(role);
    }
}
