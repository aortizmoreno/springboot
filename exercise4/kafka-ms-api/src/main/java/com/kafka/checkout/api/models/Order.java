package com.kafka.checkout.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "shippingAddress")
    private String shippingAddress;

    @Column(name = "quantity")
    private long quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;

    @Column(name = "creditCard")
    private String creditCard;

    public Order(String firstName, String lastName, String email, String shippingAddress, long quantity,
            Product product, String creditCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.quantity = quantity;
        this.product = product;
        this.creditCard = creditCard;
    }

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
