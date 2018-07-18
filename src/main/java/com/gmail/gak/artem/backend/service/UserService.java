package com.gmail.gak.artem.backend.service;

import com.gmail.gak.artem.backend.data.entity.Role;
import com.gmail.gak.artem.backend.data.entity.User;
import com.gmail.gak.artem.backend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements CrudService<User> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public JpaRepository<User, Integer> getRepository() {
        return userRepository;
    }

    @Override
    public User createNew() {
        return new User();
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public Page<User> find(Pageable pageable) {
        return userRepository.findBy(pageable);
    }

    public Page<User> findAnyMatching(String name, Role role, Pageable pageable) {
        if(name == null && role == null) {
            return find(pageable);
        }

        if(name == null) {
            return userRepository.findByRolesIn(getRolesArrayList(role), pageable);
        }

        if(role == null) {
            return userRepository.findByUsernameLikeIgnoreCase(getSearchPattern(name), pageable);
        }

        return userRepository.findByUsernameLikeIgnoreCaseAndRolesIn(getSearchPattern(name), getRolesArrayList(role), pageable);
    }

    public int countAnyMatching(String name, Role role) {
        if(name == null && role == null) {
            return (int) userRepository.count();
        }

        if(name == null) {
            return userRepository.countByRolesIn(getRolesArrayList(role));
        }

        if(role == null) {
            return userRepository.countByUsernameLikeIgnoreCase(getSearchPattern(name));
        }

        return userRepository.countByUsernameLikeIgnoreCaseAndRolesIn(getSearchPattern(name), getRolesArrayList(role));
    }

    public Page<User> findByRolesIn(List<Role> roles, Pageable pageable) {
        return userRepository.findByRolesIn(roles, pageable);
    }

    private List<Role> getRolesArrayList(Role role) {
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        return roles;
    }

    private String getSearchPattern(String str) {
        return "%" + str + "%";
    }

    public User find(Long id) {
        return userRepository.findById(id);
    }
}
