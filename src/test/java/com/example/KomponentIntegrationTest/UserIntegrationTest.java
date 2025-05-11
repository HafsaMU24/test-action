package com.example.KomponentIntegrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
@Test
    public void  testCreateAndGetUserByHttp(){
        User user = new User(null, "Fofo","fofo@example.com");

        ResponseEntity<User> postResponse = restTemplate.postForEntity("http://localhost:"+port+"/user",user,User.class);

        assertEquals(HttpStatus.OK, postResponse.getStatusCode() );

        Long userId = postResponse.getBody().getId();

        // Test restTemlate
        ResponseEntity<User> getResponse = restTemplate.getForEntity("http://localhost:" + port + "/user/"+ userId, User.class);

        assertEquals(HttpStatus.OK,getResponse.getStatusCode());
        assertEquals("Fofo", getResponse.getBody().getName());
        assertEquals("fofo@example.com",getResponse.getBody().getEmail());

    }

}