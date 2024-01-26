package com.example.electronics.service;

import com.example.electronics.model.Product;
import com.example.electronics.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        product.setProductId(UUID.randomUUID().toString().split("-")[0]);
        return  productRepository.save(product);
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(String productId) {
        return productRepository.findById(productId).get();
    }

    public List<Product> getAllProductsByPrice(Float price) {
        return productRepository.findAllProductsByPrice(price);
    }

    public List<Product> getAllProductsByStock(Integer stock) {
        return productRepository.findAllProductsByStock(stock);
    }

    public List<Product> getAllProductsByStockBetween(Integer stock1, Integer stock2) {
        return productRepository.findAllProductsByStockBetween(stock1, stock2);
    }

    public List<Product> getAllProductsByStockLessThan(Integer stock){
        return  productRepository.findAllProductsByStockLessThanEqual(stock);
    }

    public List<Product> getAllProductsByStockGreaterThan(Integer stock){
        return  productRepository.findAllProductsByStockGreaterThanEqual(stock);
    }

    public List<Product> getProductByPriceBetween(Float price1, Float price2){
        return productRepository.findAllProductsByPriceBetween(price1, price2);
    }

    public List<Product> getAllProductsByPriceLessThan(Float price) {
        return productRepository.findAllProductsByPriceLessThanEqual(price);
    }

    public  List<Product> getAllProductsByPriceGreaterThan(Float price) {
        return  productRepository.findAllProductsByPriceGreaterThanEqual(price);
    }

    public Product updateProduct(Product productRequest) {
        Product existingProduct = productRepository.findById(productRequest.getProductId()).get();
        existingProduct.setProductName(productRequest.getProductName());
        existingProduct.setProductDescription(productRequest.getProductDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setStock(productRequest.getStock());

        return productRepository.save(existingProduct);
    }

    public String deleteProduct(String productId) {
        productRepository.deleteById(productId);
        return "Product deleted";
    }
}
