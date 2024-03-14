package com.online.store.models;

import java.util.List;
import lombok.Data;

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

    @Data
    public static class ProductInfo {
        private long productId;
        private long quantity;

        public ProductInfo(long productId, long quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }
    }
}
