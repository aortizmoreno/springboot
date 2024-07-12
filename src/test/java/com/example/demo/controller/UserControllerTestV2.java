package com.example.demo.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class UserControllerTestV2 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testAddPostUser() throws Exception {
        User user = new User(0, "alberto", LocalDate.now());
        when(userService.addUser(user)).thenReturn(user);
        mockMvc.perform(post("/users")
                .content(asJsonString(user))
                .with(csrf())
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("username", "password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        // verify(userService, Mockito.times(2)).addUser(user);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testAddUser() {

    }

    @Test
    void testDeleteUser() {

    }

    @Test
    void testGetAllUsers() {

    }

    @Test
    void testGetPostForUserById() {

    }

    @Test
    void testGetUserById() {

    }

    @Test
    void testUpdateUser() {

    }
}
