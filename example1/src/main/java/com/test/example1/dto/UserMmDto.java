package com.test.example1.dto;

import lombok.Data;

import java.util.List;

import com.test.example1.model.Order;

@Data

public class UserMmDto {

    private long userId;
    private String username;
    private String firstname;
    private List<Order> orders;
}
