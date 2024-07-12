package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.demo.error.UserNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Component
public class UserService {

    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(long id) {
        Optional<User> newUser = userRepository.findById(id);
        if (newUser.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }
        return newUser;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        Optional<User> getUser = findUserById(user.getId());
        getUser.get().setName(user.getName());
        getUser.get().setBirthDate(user.getBirthDate());
        userRepository.save(getUser.get());
        return getUser.get();
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public List<Post> getPostForUserById(long id) {
        Optional<User> newUser = findUserById(id);
        return newUser.get().getPosts();
    }

    public Post createPostForUser(long id, Post post) {
        Optional<User> newUser = findUserById(id);
        post.setUser(newUser.get());
        postRepository.save(post);
        return post;
    }

}
