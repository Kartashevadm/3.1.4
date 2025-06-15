package ru.kata.spring.boot_security.demo.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class initNewUser implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public initNewUser(UserRepository userRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {

        userRepository.deleteAll();
        roleRepository.deleteAll();


        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        roleRepository.save(adminRole);
        roleRepository.save(userRole);


        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        createUser("admin", "admin@mail.com", "admin", adminRoles);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        createUser("user", "user@mail.com", "user", userRoles);
    }

    private void createUser(String username, String email, String password, Set<Role> roles) {
        if (userRepository.findByUsername(username).isEmpty()) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }

}