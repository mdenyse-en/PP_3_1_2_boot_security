package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;

@Component
public class DataBaseInitializer {
    private final RoleService roleService;

    private final UserService userService;

    @Autowired
    public DataBaseInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void initializeUsersAndRoles() {
        roleService.addRole(new Role("ROLE_USER"));
        userService.addUser(new User("user",
                "user",
                "userovich",
                12,
                "user@mail.ru",
                new HashSet<>(roleService.getRoles())));

        roleService.addRole(new Role("ROLE_ADMIN"));
        userService.addUser(new User("admin",
                "admin",
                "none",
                11,
                "admin@google.com",
                new HashSet<>(roleService.getRoles())));
    }
}
