package com.example.electronics.service;

import com.example.electronics.model.Product;
import com.example.electronics.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        try {
            product.setProductId(UUID.randomUUID().toString().split("-")[0]);
            Product savedProduct = productRepository.save(product);
            log.info("Product added successfully: {}", savedProduct);
            return savedProduct;
        } catch (Exception e) {
            log.error("Error adding product", e);
            throw new RuntimeException("Error adding product");
        }
    }

    public List<Product> findAllProducts() {
        try {
            log.info("Executing findAll query");
            return productRepository.findAll();
        } catch (Exception e) {
            log.error("Error fetching all products", e);
            throw new RuntimeException("Error fetching all products");
        }
    }

    public Product getProductById(String productId) {
        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            log.info("Product fetched by ID: {}", productId);
            return product;
        } catch (Exception e) {
            log.error("Error fetching product by ID: {}", productId, e);
            throw new RuntimeException("Error fetching product by ID");
        }
    }

    public List<Product> getAllProductsByPrice(Float price) {
        try {
            log.info("Fetching products by price: {}", price);
            return productRepository.findAllProductsByPrice(price);
        } catch (Exception e) {
            log.error("Error fetching products by price: {}", price, e);
            throw new RuntimeException("Error fetching products by price");
        }
    }

    public List<Product> getAllProductsByStock(Integer stock) {
        try {
            log.info("Fetching products by stock: {}", stock);
            return productRepository.findAllProductsByStock(stock);
        } catch (Exception e) {
            log.error("Error fetching products by stock: {}", stock, e);
            throw new RuntimeException("Error fetching products by stock");
        }
    }

    public List<Product> getAllProductsByStockBetween(Integer stock1, Integer stock2) {
        try {
            log.info("Fetching products by stock range: {} to {}", stock1, stock2);
            return productRepository.findAllProductsByStockBetween(stock1, stock2);
        } catch (Exception e) {
            log.error("Error fetching products by stock range: {} to {}", stock1, stock2, e);
            throw new RuntimeException("Error fetching products by stock range");
        }
    }

    public List<Product> getAllProductsByStockLessThan(Integer stock) {
        try {
            log.info("Fetching products by stock less than: {}", stock);
            return productRepository.findAllProductsByStockLessThanEqual(stock);
        } catch (Exception e) {
            log.error("Error fetching products by stock less than: {}", stock, e);
            throw new RuntimeException("Error fetching products by stock less than");
        }
    }

    public List<Product> getAllProductsByStockGreaterThan(Integer stock) {
        try {
            log.info("Fetching products by stock greater than: {}", stock);
            return productRepository.findAllProductsByStockGreaterThanEqual(stock);
        } catch (Exception e) {
            log.error("Error fetching products by stock greater than: {}", stock, e);
            throw new RuntimeException("Error fetching products by stock greater than");
        }
    }

    public List<Product> getProductByPriceBetween(Float price1, Float price2) {
        try {
            log.info("Fetching products by price range: {} to {}", price1, price2);
            return productRepository.findAllProductsByPriceBetween(price1, price2);
        } catch (Exception e) {
            log.error("Error fetching products by price range: {} to {}", price1, price2, e);
            throw new RuntimeException("Error fetching products by price range");
        }
    }

    public List<Product> getAllProductsByPriceLessThan(Float price) {
        try {
            log.info("Fetching products by price less than: {}", price);
            return productRepository.findAllProductsByPriceLessThanEqual(price);
        } catch (Exception e) {
            log.error("Error fetching products by price less than: {}", price, e);
            throw new RuntimeException("Error fetching products by price less than");
        }
    }

    public List<Product> getAllProductsByPriceGreaterThan(Float price) {
        try {
            log.info("Fetching products by price greater than: {}", price);
            return productRepository.findAllProductsByPriceGreaterThanEqual(price);
        } catch (Exception e) {
            log.error("Error fetching products by price greater than: {}", price, e);
            throw new RuntimeException("Error fetching products by price greater than");
        }
    }

    public Product updateProduct(Product productRequest) {
        try {
            Product existingProduct = productRepository.findById(productRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            existingProduct.setProductName(productRequest.getProductName());
            existingProduct.setProductDescription(productRequest.getProductDescription());
            existingProduct.setPrice(productRequest.getPrice());
            existingProduct.setStock(productRequest.getStock());

            Product updatedProduct = productRepository.save(existingProduct);
            log.info("Product updated successfully: {}", updatedProduct);
            return updatedProduct;
        } catch (Exception e) {
            log.error("Error updating product", e);
            throw new RuntimeException("Error updating product");
        }
    }

    public String deleteProduct(String productId) {
        try {
            productRepository.deleteById(productId);
            log.info("Product deleted with ID: {}", productId);
            return "Product deleted";
        } catch (Exception e) {
            log.error("Error deleting product with ID: {}", productId, e);
            throw new RuntimeException("Error deleting product");
        }
    }
}
