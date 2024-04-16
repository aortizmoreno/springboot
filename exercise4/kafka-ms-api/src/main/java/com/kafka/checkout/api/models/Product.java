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

    public long getProductId() {
        return this.productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageFileName() {
        return this.imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public float getPriceUSD() {
        return this.priceUSD;
    }

    public void setPriceUSD(float priceUSD) {
        this.priceUSD = priceUSD;
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
            " productId='" + getProductId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageFileName='" + getImageFileName() + "'" +
            ", priceUSD='" + getPriceUSD() + "'" +
            ", category='" + getCategory() + "'" +
            "}";
    }
}
