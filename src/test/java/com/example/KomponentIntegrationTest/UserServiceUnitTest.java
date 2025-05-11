package com.example.KomponentIntegrationTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

   @Mock
   private UserRepository userRepository;

   @InjectMocks
    private UserService userService;

   @Test
   public void testGetUserByIdReturnsUser(){
       // Arrange
       User user = new User(1L,"Fofo", "fofo@example.com");
       when(userRepository.findById(1L)).thenReturn(Optional.of(user));

       // Act
       User result = userService.getUserById(1L).orElse(null);

       // Assert
       assertNotNull(result);
       assertEquals("Fofo", result.getName());
       verify(userRepository).findById(1L);
   }
}