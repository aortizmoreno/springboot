package com.test.example1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.example1.service.UserService;
import com.test.example1.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("{id}")
    public Optional<User> getUserById(@PathVariable("id") long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("{id}")
    public User updateEmployee(@PathVariable("id") long userId, @RequestBody User user) {
        return userService.updateUser(user, userId);
    }

    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable("id") long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/byusername/{username}")
    public User getUserByUserName(@PathVariable("username") String username) {
        return userService.getUserByUserName(username);
    }

}
