package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    void createUser(User user);

    void updateUser(Long id, User user);

    void deleteUser(Long id);

    User findByEmail(String email);

    List<Role> getAllRoles();

    Optional<User> findByUsername(String username);
}