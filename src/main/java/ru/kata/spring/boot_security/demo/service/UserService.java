package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(User user);

    List<User> getUsers();

    void deleteUserById(Long id);

    User findUserById(Long id);

    User findUserByName(String username);

    void updateUser(User user);
}
