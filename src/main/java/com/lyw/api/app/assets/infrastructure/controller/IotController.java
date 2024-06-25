package com.lyw.api.app.assets.infrastructure.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lyw.api.app.assets.infrastructure.dto.LoginRequestDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/leadyourway/v1/iot")
@Tag(name = "IOT Logger Controller", description = "Used only for the iot systems")
@CrossOrigin(origins = "*")
public class IotController {

    @Value("${google.cloud.api.key}")
    private String apiKey;

    @Transactional()
    @PostMapping("/token")
    @Operation(summary = "Get token for Iot devices", description = "Get token for Iot devices to authenticate with the system")
    public String getToken(@RequestBody LoginRequestDto loginRequestDto) throws IOException {
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + apiKey;
        String body = "{\n" +
                "  \"email\": \"" + loginRequestDto.getEmail() + "\",\n" +
                "  \"password\": \"" + loginRequestDto.getPassword() + "\",\n" +
                "  \"returnSecureToken\": true\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return String.valueOf(response.getStatusCode().value());
        }
    }
}
