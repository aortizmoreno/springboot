package com.online.store.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.online.store.models.Product;
import com.online.store.models.ProductCategories;
import com.online.store.repositories.ProductCategoryRepository;
import com.online.store.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductCategoryRepository productCategoryRepository;

    @Mock
    ProductRepository productRepository;

    List<ProductCategories> listProductCategories = new ArrayList<ProductCategories>();
    List<Product> listProduct = new ArrayList<Product>();

    @Test
    void testGetAllSupportedCategories() {
        ProductCategories productCategories = new ProductCategories("test");
        listProductCategories.add(productCategories);
        when(productCategoryRepository.findAll()).thenReturn(listProductCategories);

        List<String> response = productService.getAllSupportedCategories();
        assertThat(response.size()).isEqualTo(1);
    }

    @Test
    void testGetDealsOfTheDay() {
        Product product = new Product("", "", "", 0, "");
        listProduct.add(product);
        when(productRepository.findAll()).thenReturn(listProduct);

        List<Product> response = productService.getDealsOfTheDay(1);
        assertThat(response.size()).isEqualTo(1);
    }

    @Test
    void testGetProductsByCategory() {
        Product product = new Product("", "", "", 0, "test1");
        listProduct.add(product);
        when(productRepository.findByCategory("test1")).thenReturn(listProduct);

        List<Product> response = productService.getProductsByCategory("test1");
        assertThat(response.size()).isEqualTo(1);
    }

    @Test
    void testGetAllProducts() {
        Product product = new Product("", "", "", 0, "test1");
        listProduct.add(product);
        when(productRepository.findAll()).thenReturn(listProduct);

        List<Product> response = productService.getAllProducts();
        assertThat(response.size()).isEqualTo(1);
    }

    @Test
    void testGetProductById() {
        Product product = new Product("", "", "", 0, "test1");
        when(productRepository.findById(1l)).thenReturn(Optional.of(product));
        Product result = productService.getProductById(1l);
        assertThat(result.getProductId()).isEqualTo(0L);
    }

    @Test
    void testGetProductById2() {
        when(productRepository.findById(1l)).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class, () -> {
            productService.getProductById(1l);
        });
    }
}
