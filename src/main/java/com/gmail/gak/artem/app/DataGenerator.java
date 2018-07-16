package com.gmail.gak.artem.app;

import com.gmail.gak.artem.backend.data.entity.Role;
import com.gmail.gak.artem.backend.data.entity.User;
import com.gmail.gak.artem.backend.service.RoleService;
import com.gmail.gak.artem.backend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator {

    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public DataGenerator(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public void init() {
        createRoles();
        createSuperUser();
        createUsers();
    }

    private void createRoles() {
        Role role = new Role();
        role.setName("superuser");
        roleService.save(role);

        role = new Role();
        role.setName("user");
        roleService.save(role);

        role = new Role();
        role.setName("guest");
        roleService.save(role);
    }

    private void createSuperUser() {
        Role role = roleService.findByName("superuser");

        User user = new User();
        user.setUsername("Admin");
        user.setEmail("admin@mail.com");
        user.setPassword(passwordEncoder.encode("qwerty"));
        user.addRole(role);
        userService.save(user);
    }

    private void createUsers() {
        Role role = roleService.findByName("user");

        for(int i = 1; i < 201; ++i) {
            User user = new User();
            user.setUsername("User_" + i);
            user.setEmail("user" + i + "@mail.com");
            user.setPassword(passwordEncoder.encode("123"));
            user.addRole(role);
            user.setActive(i%2 == 0);
            userService.save(user);
        }
    }
}
