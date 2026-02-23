package com.javaproject.journalApp.scheduler;

import com.javaproject.journalApp.cache.AppCache;
import com.javaproject.journalApp.entity.JournalEntry;
import com.javaproject.journalApp.entity.User;
import com.javaproject.journalApp.repository.UserRepositoryImpl;
import com.javaproject.journalApp.service.EmailService;
import com.javaproject.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;
    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSAMail() {
        List<User> users = userRepositoryImpl.getUsersForSA();

        for(User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntriesData = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList());
            String entry = String.join(" ", filteredEntriesData);
            String sentiment = sentimentAnalysisService.getSentiment(entry);

            emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", sentiment);
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache() {
        appCache.init();
    }
}
