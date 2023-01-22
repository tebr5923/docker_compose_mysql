package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    void saveRole(Role role);

    List<Role> getAllRoles();

    Optional<Role> getRoleByName(String name);

}
