package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getUsers() {
        return "index";
    }

    @GetMapping(value = "/user")
    public String showCurrentUser(Principal principal, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.findUserByName(principal.getName()));
        modelMap.addAttribute("roles1", userService.findUserByName(principal.getName()).getRoles()
                .stream()
                .filter(role -> role.getName().startsWith("ROLE_"))
                .map(role -> {
                    role.setName(role.getName().substring(5));
                    return role.getName();
                })
                .collect(Collectors.toSet()));

        return "user";
    }
}
