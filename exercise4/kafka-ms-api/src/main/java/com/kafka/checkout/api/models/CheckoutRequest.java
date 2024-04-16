package com.kafka.checkout.api.models;

import lombok.Data;
import java.util.List;

@Data
public class CheckoutRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String shippingAddress;
    private List<ProductInfo> products;
    private String creditCard;

    public CheckoutRequest(String firstName, String lastName, String email, String shippingAddress,
            List<ProductInfo> products, String creditCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.products = products;
        this.creditCard = creditCard;
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

    public List<ProductInfo> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductInfo> products) {
        this.products = products;
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
                " firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", email='" + getEmail() + "'" +
                ", shippingAddress='" + getShippingAddress() + "'" +
                ", products='" + getProducts() + "'" +
                ", creditCard='" + getCreditCard() + "'" +
                "}";
    }

    @Data
    public static class ProductInfo {
        private long productId;
        private long quantity;

        public ProductInfo(long productId, long quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        public long getProductId() {
            return this.productId;
        }

        public void setProductId(long productId) {
            this.productId = productId;
        }

        public long getQuantity() {
            return this.quantity;
        }

        public void setQuantity(long quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "{" +
                    " productId='" + getProductId() + "'" +
                    ", quantity='" + getQuantity() + "'" +
                    "}";
        }
    }
}
