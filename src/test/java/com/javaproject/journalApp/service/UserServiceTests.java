package com.javaproject.journalApp.service;

import com.javaproject.journalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @BeforeAll
    static void setUp() {
        // Load .env file
        io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.configure().ignoreIfMissing()
                .load();
        // Set system properties for Spring Boot to pick up
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "ram",
            "shyam",
            "vipul"
    })
    public void testFindByUserName(String name) {
        assertNotNull(userService.findByUserName("ram"));
        assertNotNull(userRepository.findByUserName(name), "failed for: " + name);
    }
}
