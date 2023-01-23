package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    public static final String REDIRECT_TO_LIST_USERS="redirect:/admin/users";

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String admin() {
        return "admin/admin";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/new";
    }


    @PostMapping("create_user")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam() Set<Role> roles) {
        user.setRoles(getRolesFromDB(roles));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return REDIRECT_TO_LIST_USERS;
    }


    @PatchMapping("/user/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        userService.updateUser(user);
        return REDIRECT_TO_LIST_USERS;
    }


    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return REDIRECT_TO_LIST_USERS;
    }

    @GetMapping("/user/{id}")
    public String user(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/user";
    }

    @GetMapping("/users")
    public String list(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/list";
    }

    private Set<Role> getRolesFromDB(Set<Role> roles){
        return roles.stream()
                .map(role -> roleService.getRoleByName(role.getName()).orElse(null))
                .collect(Collectors.toUnmodifiableSet());
    }

}
