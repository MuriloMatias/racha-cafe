package com.racha.cafe.ms.authMicroservice.controller;

import com.racha.cafe.ms.authMicroservice.domain.auth.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/token")
@RestController
public class AuthController {

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/")
    public ResponseEntity<String> newToken(@RequestBody User user){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", "facoffee");
        formData.add("grant_type", "password");
        formData.add("username", user.getUsername());
        formData.add("password", user.getPassword());
        formData.add("scope", "openid");

        HttpEntity<MultiValueMap<String, String>> entity =
                new HttpEntity<MultiValueMap<String, String>>(formData, headers);

        return restTemplate.postForEntity("https://auth.facoffee.hsborges.dev/realms/facoffee/protocol/openid-connect/token", entity, String.class);


    }
}
