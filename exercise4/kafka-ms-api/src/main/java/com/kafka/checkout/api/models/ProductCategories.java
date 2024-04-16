package com.kafka.checkout.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ProductCategories")
public class ProductCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "category", length = 100)
    private String category;

    public ProductCategories() {
    }

    public ProductCategories(String category) {
        this.category = category;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", category='" + getCategory() + "'" +
            "}";
    }
}

