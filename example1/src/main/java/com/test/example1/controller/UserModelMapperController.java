package com.test.example1.controller;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.example1.dto.UserMmDto;
import com.test.example1.exception.UserNotFoundException;
import com.test.example1.model.User;
import com.test.example1.service.UserService;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/modelmapper/users")
@Validated
public class UserModelMapperController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    // @GetMapping("{id}")
    // public UserMmDto getUserById(@PathVariable("id") @Min(1) long userId) throws UserNotFoundException {
    //     Optional<User> userOptional = userService.getUserById(userId);
    //     if (!userOptional.isPresent()) {
    //         throw new UserNotFoundException("user not found");
    //     }

    //     User user = userOptional.get();
    //     UserMmDto userMmDto = modelMapper.map(user, UserMmDto.class);
    //     return userMmDto;
    // }

}
