package com.javaproject.journalApp.controller;

import com.javaproject.journalApp.entity.User;
import com.javaproject.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAll();
        if(users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not any user exist yet.", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public ResponseEntity<?> createAdmin(@RequestBody User user) {
        userService.saveAdmin(user);
        return new ResponseEntity<>("Admin is created", HttpStatus.CREATED);
    }
}
