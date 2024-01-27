package com.example.electronics.repository;


import com.example.electronics.TestDataUtils;
import com.example.electronics.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@DataMongoTest
@SpringJUnitConfig
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindAllProductsByPriceBetween(){

        Product productA = TestDataUtils.createTestProductA();
        Product productB = TestDataUtils.createTestProductB();
        Product productC = TestDataUtils.createTestProductC();

        productRepository.save(productA);
        productRepository.save(productB);
        productRepository.save(productC);

        List<Product> result = productRepository.findAllProductsByPriceBetween(
                125.25F,
                250.34F
        );

        assertNotNull(result);
        assertEquals(2, result.size());

    }

    @Test
    public void testFindAllProductsByPriceLessThanEqual() {
        // Test data setup
        Float maxPrice = 90.0F;
        Product product1 = new Product("1", "Product1", "Description1", 50.0F, 10);
        Product product2 = new Product("2", "Product2", "Description2", 80.0F, 20);

        // Mocking repository behavior
        when(productRepository.findAllProductsByPriceLessThanEqual(eq(maxPrice)))
                .thenReturn(Arrays.asList(product1, product2));

        // Perform the repository method and verify the result
        List<Product> result = productRepository.findAllProductsByPriceLessThanEqual(maxPrice);
        assertEquals(2, result.size());
    }

}
