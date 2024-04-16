package com.kafka.checkout.api.models.wrappers;

import java.util.Collections;
import java.util.List;
import com.kafka.checkout.api.models.Product;
import lombok.Data;

@Data
public class ProductsWrapper {

    private List<Product> products = Collections.EMPTY_LIST;

    public ProductsWrapper(List<Product> products) {
        this.products = Collections.unmodifiableList(products);
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "{" +
            " products='" + getProducts() + "'" +
            "}";
    }
}
