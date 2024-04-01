package com.test.example1.dto;

import java.util.List;
import com.test.example1.model.Order;
import lombok.Data;

@Data
public class UserDtoV1 {

    private long userId;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
    private String ssn;
    private List<Order> orders;
}
