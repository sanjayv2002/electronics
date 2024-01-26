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

//    public List<Product> getProductByPrice(Float price1, Float price2){
//        return productRepository.findAllByPrice(price1, price2);
//    }

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
