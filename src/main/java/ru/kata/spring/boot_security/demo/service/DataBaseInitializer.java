package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;

@Component
public class DataBaseInitializer {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initializeUsersAndRoles() {
        roleService.addRole(new Role("ROLE_USER"));
        userService.addUser(new User("user",
                passwordEncoder.encode("user"),
                "userovich",
                12,
                roleService.getRoles()));

        roleService.addRole(new Role("ROLE_ADMIN"));
        userService.addUser(new User("admin",
                passwordEncoder.encode("admin"),
                "none",
                11,
                roleService.getRoles()));
    }
}
