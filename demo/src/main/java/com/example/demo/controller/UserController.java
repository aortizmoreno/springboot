package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable long id) {
        EntityModel<User> entityModel = EntityModel.of(this.userService.findUserById(id).get());
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    // @PostMapping("")
    // public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
    // System.out.println("*****user1");
    // User newUser = this.userService.addUser(user);
    // System.out.println("*****user: " + user);
    // URI location =
    // ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId())
    // .toUri();
    // return ResponseEntity.created(location).build();
    // }

    @PostMapping("")
    public User addUser(@Valid @RequestBody User user) {
        User newUser = this.userService.addUser(user);
        return newUser;
    }

    @PutMapping("")
    public User updateUser(@RequestBody User user) {
        return this.userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        this.userService.deleteUser(id);
    }

    @GetMapping("/{id}/posts")
    public List<Post> getPostForUserById(@PathVariable long id) {
        List<Post> posts = this.userService.getPostForUserById(id);
        return posts;
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<Post> addPostUser(@PathVariable long id, @RequestBody Post post) {
        Post newPost = this.userService.createPostForUser(id, post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
