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

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
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
            log.error("Inside testValidProduct: An {} Exception was thrown", e);
            fail("An error occured while testing valid product:{} ", e);
        }

    }




}
