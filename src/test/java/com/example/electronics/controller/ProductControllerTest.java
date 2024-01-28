package com.example.electronics.controller;

import com.example.electronics.TestDataUtils;
import com.example.electronics.model.Product;
import com.example.electronics.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetProductsValidResponse() throws Exception{
        Product product = TestDataUtils.createTestProductE();

        when(productService.findAllProducts()).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].productId").value(product.getProductId()))
                .andExpect(jsonPath("$[0].productName").value(product.getProductName()))
                .andExpect(jsonPath("$[0].productDescription").value(product.getProductDescription()))
                .andExpect(jsonPath("$[0].price").value(product.getPrice()))
                .andExpect(jsonPath("$[0].stock").value(product.getStock()));
    }


    @Test
    public void testCreateProduct() throws Exception {
        // Test data setup
        Product product = TestDataUtils.createTestProductA();

        // Mocking the service behavior
        when(productService.addProduct(product)).thenReturn(product);

        // Perform the POST request and verify the response
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(product.getProductId()))
                .andExpect(jsonPath("$.productName").value(product.getProductName()))
                .andExpect(jsonPath("$.productDescription").value(product.getProductDescription()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andExpect(jsonPath("$.stock").value(product.getStock()));
    }

    @Test
    public void testGetProductById() throws Exception {

        Product product = TestDataUtils.createTestProductB();
        String productId = product.getProductId();

        // save the product to db
        productService.addProduct(product);

        // Mocking the service behavior
        when(productService.getProductById(productId)).thenReturn(product);

        // Perform the GET request and verify the response
        mockMvc.perform(get("/products/{}", productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(product.getProductId()))
                .andExpect(jsonPath("$.productName").value(product.getProductName()))
                .andExpect(jsonPath("$.productDescription").value(product.getProductDescription()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andExpect(jsonPath("$.stock").value(product.getStock()));

    }


}
