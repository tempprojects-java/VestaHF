package com.gmail.gak.artem.backend.service;

import com.gmail.gak.artem.backend.data.entity.Role;
import com.gmail.gak.artem.backend.data.entity.User;
import com.gmail.gak.artem.backend.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Test
    public void findUserByEmail() {
        User user = userService.findUserByEmail("test@mail.com");
        assertEquals("TempName", user.getUsername());
    }

    @Test
    public void find() {
    }

    @Test
    public void findAnyMatching() {
        Role role = roleService.findByName("admin");
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        UserRepository repository = (UserRepository) userService.getRepository();
        Page<User> users = repository.findByRolesIn(roles, null);

        assertEquals(1, users.getTotalElements());
    }

    @Test
    public void countAnyMatching() {
    }
}