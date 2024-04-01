package com.test.example1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.example1.exception.UserNotFoundException;
import com.test.example1.model.Order;
import com.test.example1.model.User;
import com.test.example1.repository.OrderRepository;
import com.test.example1.repository.UserRepository;

@RestController
@RequestMapping("/hateoas/users")
public class OrderHateoasController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}/orders")
    public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found");
        }

        List<Order> allOrders = userOptional.get().getOrders();

        return userOptional.get().getOrders();
    }
}
