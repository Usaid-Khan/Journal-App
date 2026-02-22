package com.javaproject.journalApp.cache;

import com.javaproject.journalApp.entity.ConfigJournalAppEntity;
import com.javaproject.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public enum keys {
        WEATHER_API
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;
    public Map<String, String> cache;

    @PostConstruct
    public void init() {
        cache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            cache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }
}
