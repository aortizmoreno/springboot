package com.online.store.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import com.online.store.models.Product;
import com.online.store.models.wrappers.ProductsWrapper;
import com.online.store.services.ProductService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@AutoConfigureMockMvc
public class HomepageControllerTest {

    @InjectMocks
    HomepageController homepageController;

    @Mock
    ProductService productService;

    private MockMvc mockMvc;

    @BeforeEach
    void setMockOutput() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        when(productService.getAllSupportedCategories()).thenReturn(list);

        List<Product> mockListProductAll = new ArrayList<Product>();
        mockListProductAll.add(new Product("test1", "test1", "test1", 0, "test1"));
        mockListProductAll.add(new Product("test2", "test2", "test2", 0, "test2"));

        List<Product> mockListProductAOne = new ArrayList<Product>();
        mockListProductAOne.add(new Product("test1", "test1", "test1", 0, "test1"));

        when(productService.getDealsOfTheDay(1)).thenReturn(mockListProductAll);

        when(productService.getProductsByCategory("test1")).thenReturn(mockListProductAOne);
        when(productService.getAllProducts()).thenReturn(mockListProductAll);
    }

    @DisplayName("testGetProductCategories")
    @Test
    void testGetProductCategories() {
        String expected = "1";
        String response = homepageController.getProductCategories();
        assertThat(response).isEqualTo(expected);
    }

    @Test
    void testGetDealOfTheDay() {
        ProductsWrapper response = homepageController.getDealOfTheDay(1);
        System.err.println(response);
        assertThat(response.getProducts().size()).isEqualTo(1);
    }

    @Test
    void testGetProductForCategoryEmptyCategory() {
        ProductsWrapper response = homepageController.getProductForCategory("");
        assertThat(response.getProducts().size()).isEqualTo(2);
    }

    @Test
    void testGetProductForCategoryWithCategory() {
        ProductsWrapper response = homepageController.getProductForCategory("test1");
        assertThat(response.getProducts().size()).isEqualTo(1);
    }
}
