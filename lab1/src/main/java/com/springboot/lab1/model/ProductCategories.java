package com.springboot.lab1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="ProductCategories")
public class ProductCategories {

    private long id;
    private String name;
}
