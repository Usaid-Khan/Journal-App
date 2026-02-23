package com.javaproject.journalApp.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTests {
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @BeforeAll
    static void setUp() {
        // Load .env file
        io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.configure().ignoreIfMissing()
                .load();
        // Set system properties for Spring Boot to pick up
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
    }

    @Test
    public void testGetUsersForSA() {
        Assertions.assertNotNull(userRepositoryImpl.getUsersForSA());
    }
}
