package com.test.example1.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.test.example1.exception.UserNotFoundException;
import com.test.example1.model.User;
import com.test.example1.repository.UserRepository;
import com.test.example1.service.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) long userId) {
        try {

            Optional<User> userOptional = userService.getUserById(userId);
            User user =  userOptional.get();
           
            Set<String> fields = new HashSet<String>();
            fields.add("userId");
            fields.add("username");
            fields.add("ssn");
            fields.add("orders");

            FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
          
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
            mappingJacksonValue.setFilters(filterProvider);
            return mappingJacksonValue;
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/params/{id}")
    public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) long userId, 
    @RequestParam Set<String> fields) {
        try {

            Optional<User> userOptional = userService.getUserById(userId);
            User user =  userOptional.get();

            FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
          
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
            mappingJacksonValue.setFilters(filterProvider);
            return mappingJacksonValue;
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
