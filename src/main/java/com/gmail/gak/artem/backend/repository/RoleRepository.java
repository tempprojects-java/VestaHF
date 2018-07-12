package com.gmail.gak.artem.backend.repository;

import com.gmail.gak.artem.backend.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
