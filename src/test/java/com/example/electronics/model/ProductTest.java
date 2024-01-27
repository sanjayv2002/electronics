package com.example.electronics.model;

import com.example.electronics.TestDataUtils;
import com.example.electronics.repository.ProductRepository;
import com.example.electronics.service.ProductService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Component;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
@Component
public class ProductTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void testValidProduct(){

        try {
            Product product = TestDataUtils.createTestProductA();
            log.info("Inside testValidProduct:Creating a test product {}", product.getProductId());
            Set<ConstraintViolation<Product>> violations = validator.validate(product);
            assertEquals(0, violations.size());

            when(productRepository.save(product)).thenReturn(product);
            Product savedProduct = productService.addProduct(product);
            assertEquals(product, savedProduct);
            log.info("Inside testValidProduct:product validation test passed");
        } catch (Exception e) {
            log.error("Inside testValidProduct_ An  Exception was thrown", e);
            fail("An error occurred while testing valid product:{} ", e);
        }

    }

    @Test
    void testBlankProductName() {
        Product product = Product.builder()
                .productId("1")
                .productName("")
                .price(1000.0f)
                .stock(10)
                .build();

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(1, violations.size());
        assertEquals("Product name cannot be blank", violations.iterator().next().getMessage());
    }

    @Test
    void testNullPrice() {
        Product product = Product.builder()
                .productId("1")
                .productName("Laptop")
                .price(null)
                .stock(10)
                .build();

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(1, violations.size());
        assertEquals("Price cannot be null", violations.iterator().next().getMessage());
    }

    @Test
    void testNegativePrice() {
        Product product = Product.builder()
                .productId("1")
                .productName("Laptop")
                .price(-100.0f)
                .stock(10)
                .build();

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(1, violations.size());
        assertEquals("Price cannot be negative", violations.iterator().next().getMessage());
    }

    @Test
    void testNullStock() {
        Product product = Product.builder()
                .productId("1")
                .productName("Laptop")
                .price(250.76F)
                .stock(null)
                .build();

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(1, violations.size());
        assertEquals("Stock cannot be null", violations.iterator().next().getMessage());
    }

    @Test
    void testNegativeStock() {
        Product product = Product.builder()
                .productId("1")
                .productName("Laptop")
                .price(100.30f)
                .stock(-10)
                .build();

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(1, violations.size());
        assertEquals("Stock cannot be negative", violations.iterator().next().getMessage());
    }

}
