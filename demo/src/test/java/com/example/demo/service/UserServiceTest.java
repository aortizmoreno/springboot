package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.example.demo.error.UserNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        postRepository = Mockito.mock(PostRepository.class);
        userService = new UserService(userRepository, postRepository);
    }

    @Test
    void testAddUser() {
        User user1 = new User(0, "alberto", LocalDate.now());
        when(userRepository.save(user1)).thenReturn(user1);
        User response = userService.addUser(user1);
        assertEquals(response.getId(), user1.getId());
    }

    @Test
    void testDeleteUser() {
        User user1 = new User(0, "alberto", LocalDate.now());
        userService.deleteUser(user1.getId());
        Mockito.verify(userRepository, times(1)).deleteById(user1.getId());

        Optional<User> optional = Optional.empty();
        assertEquals(userRepository.findById(user1.getId()), optional);
    }

    @Test
    void testFindAll() {
        User user1 = new User(0, "alberto", LocalDate.now());
        User user2 = new User(1, "Mike", LocalDate.now());
        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);
        when(userRepository.findAll()).thenReturn(userList);
        List<User> response = userService.findAll();
        assertEquals(response.size(), userList.size());
    }

    @Test
    void testFindUserById() {
        User user1 = new User(0, "alberto", LocalDate.now());
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        Optional<User> response = userService.findUserById(user1.getId());
        assertEquals(response.get().getId(), user1.getId());
    }

    @Test
    void testFindUserByIdWithException() {
        User user1 = new User(0, "alberto", LocalDate.now());
        when(userRepository.findById(user1.getId())).thenReturn(Optional.empty());
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userService.findUserById(user1.getId()));
        assertEquals(exception.getMessage(), "id:" + user1.getId());
    }

    @Test
    void testUpdateUser() {
        User user1 = new User(0, "alberto", LocalDate.now());
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        User response = userService.updateUser(user1);
        assertEquals(response.getId(), user1.getId());
    }

    @Test
    void testUpdateUserWithException() {
        User user1 = new User(0, "alberto", LocalDate.now());
        when(userRepository.findById(user1.getId())).thenReturn(Optional.empty());
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userService.updateUser(user1));
        assertEquals(exception.getMessage(), "id:" + user1.getId());
    }

    @Test
    void testCreatePostForUser() {
        User user1 = new User(0, "alberto", LocalDate.now());
        Post post = new Post(0, "new post");
        post.setUser(user1);
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        Post response = userService.createPostForUser(0, post);
        assertEquals(response.getId(), post.getId());
        Mockito.verify(postRepository, times(1)).save(post);
    }

    @Test
    void testGetPostForUserById() {
        User user1 = new User(0, "alberto", LocalDate.now());
        Post post = new Post(0, "new post");
        List<Post> listPost = new ArrayList<>();
        listPost.add(post);
        user1.setPosts(listPost);
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        List<Post> response = userService.getPostForUserById(0);
        assertEquals(response.size(), user1.getPosts().size());
    }
}
