package ru.kata.spring.boot_security.demo.restTemplateClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashMap;
import java.util.Map;

public class RestClient {
    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://94.198.50.185:7081/api/users";
        var responseEntity = restTemplate.getForEntity(url, String.class);

        var headers = responseEntity.getHeaders();
        String sessionId = headers.get(HttpHeaders.SET_COOKIE).get(0);
        var body = responseEntity.getBody();
        System.out.println(headers);
        System.out.println("----------------------");
        System.out.println(body);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Cookie", sessionId);

        //------post
        System.out.println("----------post------------");

        System.out.println(httpHeaders);
        Map<String, String> postMap = new HashMap<>();
        postMap.put("id", "3");
        postMap.put("name", "James");
        postMap.put("lastName", "Brown");
        postMap.put("age", "25");
        String stringPost = "{\"id\":3,\"name\":\"James\",\"lastName\":\"Brown\",\"age\":25}";
        //HttpEntity<String> httpEntity = new HttpEntity<>(stringPost, httpHeaders);
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(postMap, httpHeaders);

        ResponseEntity<String> postResponse = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

        System.out.println("post headers:" + postResponse.getHeaders());
        System.out.println("----------------------");
        System.out.println("post body:" + postResponse.getBody());

        //------put
        System.out.println("----------put------------");

        HttpHeaders httpHeadersPut = new HttpHeaders();
        httpHeadersPut.setContentType(MediaType.APPLICATION_JSON);
        httpHeadersPut.set("Cookie", sessionId);

        String stringPut = "{\"id\":3,\"name\":\"Thomas\",\"lastName\":\"Shelby\",\"age\":23}";
        Map<String, String> putMap = new HashMap<>();
        putMap.put("id", "3");
        putMap.put("name", "Thomas");
        putMap.put("lastName", "Shelby");
        putMap.put("age", "25");
        HttpEntity<Map<String, String>> httpEntityPut = new HttpEntity<>(putMap, httpHeadersPut);

        ResponseEntity<String> putResponse = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);

        System.out.println("put headers:" + putResponse.getHeaders());
        System.out.println("----------------------");
        System.out.println("put body:" + putResponse.getBody());

        //------delete
        System.out.println("----------delete------------");

        HttpHeaders httpHeadersDel = new HttpHeaders();
        httpHeadersDel.set("Cookie", sessionId);

        HttpEntity<String> httpEntityDelete = new HttpEntity<>(httpHeadersDel);

        ResponseEntity<String> delResponse = restTemplate.exchange(url + "/3", HttpMethod.DELETE, httpEntity, String.class);

        System.out.println("del headers:" + delResponse.getHeaders());
        System.out.println("----------------------");
        System.out.println("del body:" + delResponse.getBody());

        System.out.println(postResponse.getBody() + putResponse.getBody() + delResponse.getBody());
//        5ebfeb18f2859345a3
        //5ebfebe7cb975dfcf9


        //        String getUrl = "http://localhost:8080/api/v1/users/1";
//        var getResponse = restTemplate.getForObject(getUrl, String.class);
//
//        UserMapper userMapper = new UserMapper();
//        System.out.println("-------getForObject-------");
//        System.out.println(getResponse);
//        System.out.println(userMapper.jasonToUser(getResponse));
//
//
//        System.out.println("-------postForObject-------");
//        Map<String, String> postJson = new HashMap<>();
//        postJson.put("firstName", "firstName");
//        postJson.put("lastName", "lastName");
//        postJson.put("email", "email");
//        postJson.put("password", "password");
//        postJson.put("roles", "ROLE_USER");
//        HttpEntity<Map<String, String>> request= new HttpEntity<>(postJson);
//        User postUser = new User();
//        postUser.setFirstName("postFirstName");
//        postUser.setLastName("postLastName");
//        postUser.setAge(35);
//        postUser.setEmail("post@mail.ru");
//        postUser.setPassword("postpwd");
//
//        Role role = new Role();
//        role.setId(1);
//        role.setName("ROLE_USER");
//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add(role);
//        postUser.setRoles(roleSet);
//        String postUrl = "http://localhost:8080/api/v1/users";
//        var postResponse = restTemplate.postForObject(postUrl, postJson, User.class);
//        System.out.println(postResponse);

    }
}
