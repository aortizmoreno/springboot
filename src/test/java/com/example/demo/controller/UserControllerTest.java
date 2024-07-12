package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

public class UserControllerTest {

    private UserController userController;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    void testGetAllUsers() {
        List<User> listUser = new ArrayList<>();
        listUser.add(new User(0, "alberto", LocalDate.now()));
        when(userService.findAll()).thenReturn(listUser);
        List<User> response = userController.getAllUsers();
        assertEquals(response.size(), listUser.size());
    }

    @Test
    void testGetUserById() {
        User user = new User(0, "alberto", LocalDate.now());
        when(userService.findUserById(user.getId())).thenReturn(Optional.of(user));
        EntityModel<User> response = userController.getUserById(user.getId());
        assertEquals(response.getContent().getId(), user.getId());
    }

    @Test
    void testAddUser() {
        User user = new User(0, "alberto", LocalDate.now());
        when(userService.addUser(user)).thenReturn(user);
        User response = userController.addUser(user);
        assertEquals(response.getId(), user.getId());
    }

    @Test
    void testUpdateUser() {
        User user = new User(0, "alberto", LocalDate.now());
        when(userService.updateUser(user)).thenReturn(user);
        User response = userController.updateUser(user);
        assertEquals(response.getId(), user.getId());
    }

    @Test
    void testDeleteUser() {
        User user = new User(0, "alberto", LocalDate.now());
        userController.deleteUser(user.getId());
        Optional<User> optional = Optional.empty();
        assertEquals(userService.findUserById(user.getId()), optional);
    }

    @Test
    void testGetPostForUserById() {
        User user = new User(0, "alberto", LocalDate.now());
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(0, "post1"));
        when(userService.getPostForUserById(user.getId())).thenReturn(posts);
        List<Post> response = userController.getPostForUserById(user.getId());
        assertEquals(response.size(), posts.size());
    }

    @Test
    void testAddPostUser() {
        User user = new User(0, "alberto", LocalDate.now());
        Post post = new Post(0, "post1");
        when(userService.createPostForUser(user.getId(), post)).thenReturn(post);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<Post> response = userController.addPostUser(user.getId(), post);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    }
}
