package com.javaproject.journalApp.cron;

import com.javaproject.journalApp.scheduler.UserScheduler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootTest
//@EnableKafka
public class UserSchedulersTest {
    @Autowired
    private UserScheduler userScheduler;

    @BeforeAll
    static void setUp() {
        // Load .env file
        io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.configure().ignoreIfMissing()
                .load();
        // Set system properties for Spring Boot to pick up
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
    }

    @Test
    public void testFetchUsersAndSendSaMail() {
        userScheduler.fetchUsersAndSendSAMail();
    }
}
