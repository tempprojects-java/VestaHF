package com.gmail.gak.artem.backend.repository;

import com.gmail.gak.artem.backend.data.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String admin);

    Page<Role> findBy(Pageable pageable);

    Page<Role> findByNameLikeIgnoreCase(String searchPattern, Pageable pageable);

    int countByNameLikeIgnoreCase(String searchPattern);
}
