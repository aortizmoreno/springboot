package com.kafka.checkout.broker.message;

public class Product {

    private long productId; 
    private String name;
    private String description;
    private String imageFileName;
    private float priceUSD;
    private String category;

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
