package com.test.example1.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonView;

@Data
@Entity
@Table(name = "User")
// @JsonIgnoreProperties({"first_name", "last_name"})
// @JsonFilter("userFilter")

public class User extends RepresentationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.External.class)
    private long userId;

    @NotEmpty(message = "Useername is mandatory field, Please provide username")
    @Column(name = "user_name", length = 50, nullable = false, unique = true)
    @JsonView(Views.External.class)
    private String username;

    @Size(min = 2, max = 50, message = "first name should have at least 2 characters")
    @Column(name = "first_name", length = 50, nullable = false)
    @JsonView(Views.External.class)
    private String firstname;

    @Column(name = "last_name", length = 50, nullable = false)
    @JsonView(Views.External.class)
    private String lastname;

    @Column(name = "email", length = 50, nullable = false)
    @JsonView(Views.External.class)
    private String email;

    @Column(name = "role", length = 50, nullable = false)
    @JsonView(Views.Internal.class)
    private String role;

    // @JsonIgnore
    @Column(name = "ssn", length = 50, nullable = false, unique = true)
    @JsonView(Views.Internal.class)
    private String ssn;

    @OneToMany(mappedBy = "user")
    @JsonView(Views.Internal.class)
    private List<Order> orders;

    @Column(name = "address")
    private String address;

}
