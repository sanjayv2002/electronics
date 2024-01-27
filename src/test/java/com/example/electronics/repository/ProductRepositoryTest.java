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
    public void testFindAllProductsByPriceBetween() {

        productRepository.deleteAll();

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

        productRepository.deleteAll();

        Product productA = TestDataUtils.createTestProductA();
        Product productB = TestDataUtils.createTestProductB();

        productRepository.save(productA);
        productRepository.save(productB);

        List<Product> result = productRepository.findAllProductsByPriceLessThanEqual(250.34F);

        assertNotNull(result);
        assertEquals(2, result.size());

    }

    @Test
    public void testFindAllProductsByPriceGreaterThanEqual(){

        productRepository.deleteAll();

        Product productA = TestDataUtils.createTestProductA();
        Product productB = TestDataUtils.createTestProductB();

        productRepository.save(productA);
        productRepository.save(productB);

        List<Product> result = productRepository.findAllProductsByPriceGreaterThanEqual(100.00F);

        assertNotNull(result);
        assertEquals(2, result.size());

    }

    @Test
    public void testFindAllProductsByStockBetween() {

        productRepository.deleteAll();

        Product productA = TestDataUtils.createTestProductA();
        Product productD = TestDataUtils.createTestProductD();

        productRepository.save(productA);
        productRepository.save(productD);

        List<Product> result = productRepository.findAllProductsByStockBetween(10, 25);

        assertNotNull(result);
        assertEquals(2, result.size());

    }

    @Test
    public void testFindAllProdutsByStockLessThanEqual() {

        productRepository.deleteAll();

        Product productA = TestDataUtils.createTestProductA();
        Product productC = TestDataUtils.createTestProductC();

        productRepository.save(productA);
        productRepository.save(productC);

        List<Product> result = productRepository.findAllProductsByStockLessThanEqual(40);

        assertNotNull(result);
        assertEquals(2 , result.size());

    }

    @Test
    public void testFindAllProductsByStockGreaterThanEqual(){

        productRepository.deleteAll();

        Product productA = TestDataUtils.createTestProductA();
        Product productC = TestDataUtils.createTestProductC();

        productRepository.save(productA);
        productRepository.save(productC);

        List<Product> result = productRepository.findAllProductsByStockGreaterThanEqual(5);

        assertNotNull(result);
        assertEquals(2, result.size());

    }

    @Test
    public void testFindAllProductsByPrice() {

        productRepository.deleteAll();

        Product productA = TestDataUtils.createTestProductA();
        Product productB = TestDataUtils.createTestProductB();
        Product productC = TestDataUtils.createTestProductC();

        productA.setPrice(250.55F);
        productB.setPrice(250.55F);
        productC.setPrice(250.55F);

        productRepository.save(productA);
        productRepository.save(productB);
        productRepository.save(productC);

        List<Product> result = productRepository.findAllProductsByPrice(250.55F);

        assertNotNull(result);
        assertEquals(3, result.size());

    }

    @Test
    public void testFindAllProductsByStock() {

        productRepository.deleteAll();

        Product productA = TestDataUtils.createTestProductA();
        Product productB = TestDataUtils.createTestProductB();
        Product productC = TestDataUtils.createTestProductC();

        productA.setStock(20);
        productB.setStock(20);
        productC.setStock(20);

        productRepository.save(productA);
        productRepository.save(productB);
        productRepository.save(productC);

        List<Product> result = productRepository.findAllProductsByStock(20);

        assertNotNull(result);
        assertEquals(3, result.size());

    }


}
