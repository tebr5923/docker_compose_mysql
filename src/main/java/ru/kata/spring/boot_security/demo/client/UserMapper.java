package ru.kata.spring.boot_security.demo.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.Set;

public class UserMapper {

    public User jasonToUser(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var object = mapper.readTree(response);

        User user = new User();
        user.setId(object.get("id").asLong());
        user.setFirstName(object.get("firstName").asText());
        user.setLastName(object.get("lastName").asText());
        user.setAge(object.get("age").asInt());
        user.setEmail(object.get("email").asText());
        user.setPassword(object.get("password").asText());

        Set<Role> roles = new HashSet<>();
        var jsonRoles = object.get("roles").elements();
        while (jsonRoles.hasNext()) {
            Role role = new Role();
            var nodeRole = jsonRoles.next();
            role.setId(nodeRole.get("id").asInt());
            role.setName(nodeRole.get("name").asText());
            roles.add(role);
        }
        user.setRoles(roles);
        return user;
    }
}
