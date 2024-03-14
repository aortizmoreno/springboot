package com.online.store.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.online.store.models.Product;
import com.online.store.repositories.ProductCategoryRepository;
import com.online.store.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductRepository productRepository;

    // public String getCategories() {
    //     return productCategoryRepository.findAllCategories();
    // }

    public List<String> getAllSupportedCategories() {
        return productCategoryRepository.findAll()
                .stream()
                .map(productoCategory -> productoCategory.getCategory())
                .collect(Collectors.toList());
    }

    public List<Product> getDealsOfTheDay(int atMostNumberOfProducts){
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String productCategory){
        return productRepository.findByCategory(productCategory);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(long id){
        return productRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException(String.format("Product with id %s doesn't exist", id)));
    }
}
