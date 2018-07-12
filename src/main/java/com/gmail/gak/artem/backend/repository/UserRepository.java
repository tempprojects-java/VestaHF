package com.gmail.gak.artem.backend.repository;

import com.gmail.gak.artem.backend.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);
}
