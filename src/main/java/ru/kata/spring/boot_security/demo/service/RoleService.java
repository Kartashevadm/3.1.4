package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

@Service
public interface RoleService {
    Role findById(Long id);
    List<Role> getAllRoles();

}
