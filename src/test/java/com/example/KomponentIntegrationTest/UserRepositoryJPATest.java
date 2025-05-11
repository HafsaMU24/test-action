package com.example.KomponentIntegrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
//@Rollback(value = false)
public class UserRepositoryJPATest {

    @Autowired
   private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindByEmail(){
        User user = new User(null, "Fofo","fofo@example.com");
         entityManager.persistAndFlush(user);
       // entityManager.persist(user);
      // massa annat
     // entityManager.flush();

        // Act
       Optional<User> foundUser = userRepository.findByEmail("fofo@example.com");

        //Assert
        assertTrue(foundUser.isPresent());
        assertEquals("Fofo", foundUser.get().getName());

    }
}
