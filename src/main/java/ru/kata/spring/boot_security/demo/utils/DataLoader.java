package ru.kata.spring.boot_security.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void loadDataToDB(){
        Role roleUser = new Role("ROLE_USER");
            Role roleAdmin = new Role("ROLE_ADMIN");
            roleService.saveRole(roleUser);
            roleService.saveRole(roleAdmin);

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(roleAdmin);
            User admin = new User(
                    "AdminFN",
                    "AdminLN",
                    "admin",
                    passwordEncoder.encode("admin"),
                    adminRoles);
            userService.saveUser(admin);

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(roleUser);
            User user = new User(
                    "UserFN",
                    "UserLN",
                    "user",
                    passwordEncoder.encode("user"),
                    userRoles);
            userService.saveUser(user);
    }
}
