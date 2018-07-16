package com.gmail.gak.artem.backend.service;

import com.gmail.gak.artem.backend.data.entity.Role;
import com.gmail.gak.artem.backend.repository.RoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements CrudService<Role> {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public JpaRepository<Role, Integer> getRepository() {
        return roleRepository;
    }

    @Override
    public Role createNew() {
        return new Role();
    }

    public Role findByName(String admin) {
        return roleRepository.findByName(admin);
    }
}
