package com.gmail.gak.artem.backend.repository;

import com.gmail.gak.artem.backend.data.entity.Role;
import com.gmail.gak.artem.backend.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);

    Page<User> findBy(Pageable pageable);

    Page<User> findByRolesIn(List<Role> roles, Pageable pageable);

    Page<User> findByUsernameLikeIgnoreCase(String nameFilter, Pageable pageable);

    Page<User> findByUsernameLikeIgnoreCaseAndRoles(String nameFilter, Role role, Pageable pageable);

    int countByRolesIn(List<Role> roles);

    int countByUsernameLikeIgnoreCase(String nameFilter);

    int countByUsernameLikeIgnoreCaseAndRolesIn(String nameFilter, List<Role> roles);

    Page<User> findByUsernameLikeIgnoreCaseAndRolesIn(String nameFilter, List<Role> roles, Pageable pageable);

    User findById(Long id);
}