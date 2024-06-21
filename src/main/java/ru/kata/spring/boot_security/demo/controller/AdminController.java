package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    public String getUsers(ModelMap model) {
        model.addAttribute("list", userService.getUsers());

        return "usersList";
    }

    @GetMapping(value = "/add")
    public String addNewUser(ModelMap modelMap) {
        User user = new User();
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("roles", roleService.getRoles());
        return "add";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);

        return "redirect:/admin/users";
    }

    @PostMapping(value = "/delete")
    public String addNewUsers(@RequestParam(value = "id") Long id) {
        userService.deleteUserById(id);

        return "redirect:/admin/users";
    }

    @GetMapping(value = "/edit")
    public String addEditUsers(@RequestParam(value = "id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.findUserById(id));
        modelMap.addAttribute("allRoles", roleService.getRoles());

        return "edit";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);

        return "redirect:/admin/users";
    }
}
