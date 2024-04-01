package com.test.example1.controller;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.example1.dto.UserDtoV1;
import com.test.example1.dto.UserDtoV2;
import com.test.example1.dto.UserMmDto;
import com.test.example1.exception.UserNotFoundException;
import com.test.example1.model.User;
import com.test.example1.service.UserService;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/versioning/uri/users")
@Validated
public class UserUriVersioningController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping({ "/v1.0/{id}", "/v1.1/{id}" })
    public UserDtoV1 getUserById(@PathVariable("id") @Min(1) long userId) throws UserNotFoundException {
        Optional<User> userOptional = userService.getUserById(userId);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("user not found");
        }

        User user = userOptional.get();
        UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
        return userDtoV1;
    }

    @GetMapping("/v2.0/{id}")
    public UserDtoV2 getUserById2(@PathVariable("id") @Min(1) long userId) throws UserNotFoundException {
        Optional<User> userOptional = userService.getUserById(userId);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("user not found");
        }

        User user = userOptional.get();
        UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
        return userDtoV2;
    }

}
