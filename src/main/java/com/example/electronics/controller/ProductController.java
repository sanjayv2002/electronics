package com.example.electronics.controller;

import com.example.electronics.model.Product;
import com.example.electronics.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createTask(
            @RequestBody Product product
    ) {
        return productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable  String productId) {
        return productService.getProductById(productId);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable String productId){
        return productService.deleteProduct(productId);
    }
}
