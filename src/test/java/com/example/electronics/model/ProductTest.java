package com.example.electronics.model;

import com.example.electronics.TestDataUtils;
import com.example.electronics.repository.ProductRepository;
import com.example.electronics.service.ProductService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void testValidProduct(){
        Product product = TestDataUtils.createTestProductA();

        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(0, violations.size());

        when(productRepository.save(product)).thenReturn(product);
        Product savedProduct = productService.addProduct(product);
        assertEquals(product, savedProduct);

    }


}
