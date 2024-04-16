package com.kafka.checkout.api.models;

import com.kafka.checkout.broker.message.Product;

public class Order {

    private long orderId;
    private String firstName;
    private String lastName;
    private String email;
    private String shippingAddress;
    private long quantity;
    private Product product;
    private String creditCard;

    public long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCreditCard() {
        return this.creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "{" +
            " orderId='" + getOrderId() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", shippingAddress='" + getShippingAddress() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", product='" + getProduct() + "'" +
            ", creditCard='" + getCreditCard() + "'" +
            "}";
    }
}
