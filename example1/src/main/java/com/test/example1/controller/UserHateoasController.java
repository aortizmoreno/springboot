package com.test.example1.controller;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.test.example1.exception.UserNotFoundException;
import com.test.example1.model.Order;
import com.test.example1.model.User;
import com.test.example1.repository.UserRepository;
import com.test.example1.service.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class UserHateoasController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public CollectionModel<User> getAllUsers() throws UserNotFoundException {
        List<User> allUsers = userService.getAllUser();
        for (User user : allUsers) {
            Link selfLink = WebMvcLinkBuilder.linkTo(UserHateoasController.class).slash(user.getUserId()).withSelfRel();
            user.add(selfLink);
            List<Order> listOrders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
                    .getAllOrders(user.getUserId());
            Link ordersLink = WebMvcLinkBuilder.linkTo(listOrders).withRel("all-orders");
            user.add(ordersLink);
        }

        Link selfLinkGetAllUsers = WebMvcLinkBuilder.linkTo(UserHateoasController.class).withSelfRel();
        CollectionModel<User> result = CollectionModel.of(allUsers, selfLinkGetAllUsers);
        return result;
    }

    @GetMapping("{id}")
    public Optional<User> getUserById(@PathVariable("id") @Min(1) long userId) {
        try {
            Optional<User> userOptional = userService.getUserById(userId);
            User user = userOptional.get();
            Long userId2 = user.getUserId();
            // Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(this.getClass().re);
            // Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId2);
            Link selfLink = WebMvcLinkBuilder.linkTo(UserHateoasController.class).slash(userId2).withSelfRel();
            user.add(selfLink);
            return userOptional;

        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
