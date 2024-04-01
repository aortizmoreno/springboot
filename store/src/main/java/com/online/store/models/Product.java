package com.online.store.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId; 

    @Column(name="name", length = 50)
    private String name;

    @Column(name="description", length = 400)
    private String description;

    @Column(name="imageFileName", length = 400)
    private String imageFileName;

    @Column(name="priceUSD")
    private float priceUSD;

    @Column(name="category", length = 50)
    private String category;

    protected Product() {
    }

    public Product(String name, String description, String imageFileName, float priceUSD, String category) {
        this.name = name;
        this.description = description;
        this.imageFileName = imageFileName;
        this.priceUSD = priceUSD;
        this.category = category;
    }

}
