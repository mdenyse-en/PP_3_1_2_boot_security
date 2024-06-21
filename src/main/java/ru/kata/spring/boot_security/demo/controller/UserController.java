package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getUsers() {
        return "index";
    }

    @GetMapping(value = "/user")
    public String showCurrentUser(Principal principal, ModelMap modelMap) {
        System.out.println(principal.getName());
        modelMap.addAttribute(userService.findUserByName(principal.getName()));

        return "user";
    }
}
