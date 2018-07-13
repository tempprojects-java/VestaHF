package com.gmail.gak.artem;

import com.gmail.gak.artem.backend.data.entity.Role;
import com.gmail.gak.artem.backend.data.entity.User;
import com.gmail.gak.artem.backend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserService userService, PasswordEncoder passwordEncoder) {
        return (String... strings) -> {
            Role role = new Role();
            role.setName("admin");

            User  user = new User();
            user.setUsername("TempName");
            user.setEmail("test@mail.com");
            user.setPassword(passwordEncoder.encode("123"));
            user.addRole(role);
            userService.save(user);
        };
    }
}
