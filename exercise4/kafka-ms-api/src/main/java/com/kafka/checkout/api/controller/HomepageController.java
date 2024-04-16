package com.kafka.checkout.api.controller;

import org.springframework.web.bind.annotation.RestController;
import com.kafka.checkout.api.models.wrappers.ProductsWrapper;
import com.kafka.checkout.api.service.ProductService;
import com.kafka.checkout.broker.producer.CategoriesProducer;
import com.kafka.checkout.broker.producer.DealOfTheDayProducer;
import com.kafka.checkout.broker.producer.ProductsProducer;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HomepageController {

    @Autowired
    private CategoriesProducer categoriesProducer;

    @Autowired
    private ProductsProducer productsProducer;

    @Autowired
    private DealOfTheDayProducer dealOfTheDayProducer;

    ProductService productService;

    public HomepageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/categories")
    public String getProductCategories() {

        String productCategories = productService.getAllSupportedCategories()
                .stream()
                .collect(Collectors.joining(","));
        categoriesProducer.publish(productCategories);
        return productCategories;
    }

    @GetMapping("/deal_of_the_day/{number_of_products}")
    public ProductsWrapper getDealOfTheDay(@PathVariable(name = "number_of_products") int numberOfProducts) {
        ProductsWrapper productsWrapper = new ProductsWrapper(productService.getDealsOfTheDay(numberOfProducts));
        dealOfTheDayProducer.publish(productsWrapper.getProducts());
        return productsWrapper;
    }

    @GetMapping("/products")
    public ProductsWrapper getProductForCategory(@RequestParam(name = "category", required = false) String category) {

        ProductsWrapper productsWrapper;
        if (category != null && !category.isEmpty()) {
            productsWrapper = new ProductsWrapper(
                productService.getProductsByCategory(category));
                productsProducer.publish(productsWrapper.getProducts());
            return productsWrapper;
        }

        productsWrapper = new ProductsWrapper(productService.getAllProducts());
        productsProducer.publish(productsWrapper.getProducts());
        return productsWrapper;
    }
}
