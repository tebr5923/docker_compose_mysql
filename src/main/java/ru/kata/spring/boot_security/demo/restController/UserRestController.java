package ru.kata.spring.boot_security.demo.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User user(@PathVariable("id") Long id) {
//        model.addAttribute("user", userService.getUserById(id));
//        model.addAttribute("roles", roleService.getAllRoles());
        return userService.getUserById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> list() {
        return userService.getAllUsers();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user,
                       @RequestBody Set<Role> roles) {
        user.setRoles(getRolesFromDB(roles));
        userService.saveUser(user);
        return user;
    }

    @PutMapping("/{id}")
    public User update(@PathVariable("id") Long id,
                         @RequestBody User user,
                         @RequestBody Set<Role> roles) {
        user.setRoles(getRolesFromDB(roles));
        user.setId(id);
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }


    private Set<Role> getRolesFromDB(Set<Role> roles) {
        return roles.stream()
                //.map(role -> roleService.getRoleByName(role.getName()).orElse(null))
                .map(role -> roleService.getRoleByName(role.getName()).orElseThrow(() -> new RuntimeException(role.getName() + " is absent in DB")))
                .collect(Collectors.toUnmodifiableSet());
    }
}
