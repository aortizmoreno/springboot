package com.online.store.models.wrappers;

import java.util.Collections;
import java.util.List;
import org.hibernate.mapping.Collection;
import com.online.store.models.Product;
import lombok.Data;

@Data
public class ProductsWrapper {

    private List<Product> products = Collections.EMPTY_LIST;

    public ProductsWrapper(List<Product> products) {
        this.products = Collections.unmodifiableList(products);
    }
}
