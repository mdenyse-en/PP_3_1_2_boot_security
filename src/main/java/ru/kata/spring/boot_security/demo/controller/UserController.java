package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String getUsers() {
        return "index";
    }

    @GetMapping(value = "/user")
    public String showCurrentUser(Principal principal, ModelMap modelMap) {
        System.out.println(principal.getName());

        modelMap.addAttribute("user", new User("user1", "user2", "user3", 12, null));

        return "user";
    }
}
