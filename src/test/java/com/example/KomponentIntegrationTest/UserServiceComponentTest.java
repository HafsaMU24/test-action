package com.example.KomponentIntegrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceComponentTest {

    @Autowired
    UserService userService;

    @Test
    public void testCreateAndFetchUser(){
        //Arrange
        User user = new User(null,"Fofo", "fofo@example.com");

        //Act
        User savedUser = userService.createUser(user);
        User fetchedUser= userService.getUserById(savedUser.getId()).orElse(null);

        //Assert
        assertEquals("Fofo",fetchedUser.getName());


    }

}
