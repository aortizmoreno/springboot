package com.online.store.controllers;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.online.store.models.wrappers.ProductsWrapper;
import com.online.store.services.ProductService;

@RestController
public class HomepageController {

    // @Autowired
    ProductService productService;

    public HomepageController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/categories")
    public String getProductCategories() {
        return productService.getAllSupportedCategories()
                .stream()
                .collect(Collectors.joining(","));
    }

    @GetMapping("/deal_of_the_day/{number_of_products}")
    public ProductsWrapper getDealOfTheDay(@PathVariable(name = "number_of_products") int numberOfProducts) {
        return new ProductsWrapper(productService.getDealsOfTheDay(numberOfProducts));
    }

    @GetMapping("/products")
    public ProductsWrapper getProductForCategory(@RequestParam(name = "category", required = false) String category) {

        if (category != null && !category.isEmpty()) {
            return new ProductsWrapper(
                    productService.getProductsByCategory(category));
        }

        return new ProductsWrapper(productService.getAllProducts());
    }
}
