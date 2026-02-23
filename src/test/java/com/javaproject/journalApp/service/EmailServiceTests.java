package com.javaproject.journalApp.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @BeforeAll
    static void setUp() {
        // Load .env file
        io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.configure().ignoreIfMissing()
                .load();
        // Set system properties for Spring Boot to pick up
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
    }

    @Test
    public void testSendEmail() {
        emailService.sendEmail(
                "ik157099@gmail.com",
                "Testing Java Mail Sender",
                "Hi, app kaise hain?"
        );
    }
}
