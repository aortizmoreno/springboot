package com.test.example1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.example1.repository.UserRepository;
import com.test.example1.model.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public User updateUser(User user, long id) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }

    public User getUserByUserName(String username){
        return userRepository.findByusername(username);
    }
}
