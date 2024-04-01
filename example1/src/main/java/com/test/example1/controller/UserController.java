package com.test.example1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import com.test.example1.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import com.test.example1.exception.UserNotFoundException;
import com.test.example1.model.User;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("{id}")
    public Optional<User> getUserById(@PathVariable("id") @Min(1) long userId) {
        try {
            return userService.getUserById(userId);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

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

    @PostMapping
    public ResponseEntity<Void> saveEmployee(@Valid @RequestBody User user, UriComponentsBuilder builder, BindingResult bindingResult) {
        try {
            userService.saveUser(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
