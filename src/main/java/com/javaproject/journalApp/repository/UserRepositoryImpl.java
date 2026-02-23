package com.javaproject.journalApp.repository;

import com.javaproject.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUsersForSA() {
        Query query = new Query();
//        Criteria criteria = new Criteria();

        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

//        query.addCriteria(criteria.andOperator(
//                Criteria.where("email").exists(true),
//                Criteria.where("sentimentAnalysis").is(true)));

        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
        return users;
    }
}
