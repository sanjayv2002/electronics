package com.example.electronics.service;

import com.example.electronics.TestDataUtils;
import com.example.electronics.model.Product;
import com.example.electronics.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testAddProduct(){

        Product product = TestDataUtils.createTestProductE();
        Product result = productService.addProduct(product);
        assertEquals(product, result);

    }

}
