package com.gmail.gak.artem.backend.service;

import com.gmail.gak.artem.backend.data.entity.Role;
import com.gmail.gak.artem.backend.repository.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Role> find(Pageable pageable) {
        return roleRepository.findBy(pageable);
    }

    public Page<Role> findAnyMatching(String name, Pageable pageable) {
        if(name == null) {
            return find(pageable);
        }

        return roleRepository.findByNameLikeIgnoreCase(getSearchPattern(name), pageable);
    }

    public int countAnyMatching(String name) {
        if(name == null) {
            return count();
        }

        return roleRepository.countByNameLikeIgnoreCase(getSearchPattern(name));
    }

    private String getSearchPattern(String str) {
        return "%" + str + "%";
    }
}
