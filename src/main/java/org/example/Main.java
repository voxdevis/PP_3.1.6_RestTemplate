package org.example;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


public class Main {
    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://94.198.50.185:7081/api/users";

        ResponseEntity<String> response = restTemplate.getForEntity(url,
                String.class);

        System.out.println(response.getBody());
        System.out.println(response.getHeaders());
        String cookie = response.getHeaders().getFirst("Set-Cookie");
        System.out.println(cookie);

        User user = new User(3L, "James", "Brown", (byte) 26);
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Set-Cookie", cookie);
        headers.add("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("CODE1: " + response.getBody());

        user.setName("Thomas");
        user.setLastName("Shelby");
        HttpEntity<User> entityPut = new HttpEntity<>(user, headers);


//        User user4 = new User(3L, "Thomas", "Shelby", (byte) 26);
//        HttpHeaders headers4 = new HttpHeaders();
//        headers4.add("Set-Cookie", cookie);
//        HttpEntity<User> entity4 = new HttpEntity<>(user4, headers4);

        response = restTemplate.exchange(url, HttpMethod.PUT, entityPut, String.class);
        System.out.println("CODE2: " + response.getBody());

        response = restTemplate.exchange(url+"/3", HttpMethod.DELETE, entity, String.class);
        System.out.println("CODE3: " + response.getBody());



//        User user = restTemplate.getForObject(url, User.class);

//        System.out.println(user.toString());
//         = restTemplate.getForObject("http://94.198.50.185:7081/api/users",
//                User[].class);

 //       User[] users

//       UserList response = restTemplate.getForObject(
//                url,
//                UserList.class);
//        List<User> users = response.getUsers();
//        users.forEach(u-> System.out.println(u.toString()));
//       //

    }
}