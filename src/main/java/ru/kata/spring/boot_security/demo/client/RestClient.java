package ru.kata.spring.boot_security.demo.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import ru.kata.spring.boot_security.demo.model.User;

public class RestClient {
    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/api/v1/users/1";
        var response = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        var object = mapper.readTree(response);

        User user = new User();
        user.setId(object.get("id").asLong());
        user.setFirstName(object.get("firstName").asText());
        user.setLastName(object.get("lastName").asText());
        user.setAge(object.get("age").asInt());
        user.setEmail(object.get("email").asText());
        user.setPassword(object.get("password").asText());
       // user.setFirstName(object.get(""));
        System.out.println(response);
        System.out.println(user);


    }
}
