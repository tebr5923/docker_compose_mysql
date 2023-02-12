package ru.kata.spring.boot_security.demo.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import ru.kata.spring.boot_security.demo.dao.RoleDaoImpl;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RestClient {
    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        String getUrl = "http://localhost:8080/api/v1/users/1";
        var getResponse = restTemplate.getForObject(getUrl, String.class);

        UserMapper userMapper = new UserMapper();
        System.out.println("-------getForObject-------");
        System.out.println(getResponse);
        System.out.println(userMapper.jasonToUser(getResponse));


        System.out.println("-------postForObject-------");
        Map<String, String> postJson = new HashMap<>();
        postJson.put("firstName", "firstName");
        postJson.put("lastName", "lastName");
        postJson.put("email", "email");
        postJson.put("password", "password");
        postJson.put("roles", "ROLE_USER");
        HttpEntity<Map<String, String>> request= new HttpEntity<>(postJson);
        /*User postUser = new User();
        postUser.setFirstName("postFirstName");
        postUser.setLastName("postLastName");
        postUser.setAge(35);
        postUser.setEmail("post@mail.ru");
        postUser.setPassword("postpwd");

        Role role = new Role();
        role.setId(1);
        role.setName("ROLE_USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        postUser.setRoles(roleSet);*/
        String postUrl = "http://localhost:8080/api/v1/users";
        var postResponse = restTemplate.postForObject(postUrl, postJson, User.class);
        System.out.println(postResponse);

    }
}
