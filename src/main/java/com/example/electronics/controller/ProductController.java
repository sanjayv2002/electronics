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

    @GetMapping("/price/{price}")
    public List<Product> getProductsByPrice(@PathVariable Float price) { return productService.getAllProductsByPrice(price);}

    @GetMapping("/pl/{price}")
    public List<Product> getProductsByPriceLess(@PathVariable Float price) {
        return productService.getAllProductsByPriceLessThan(price);
    }

    @GetMapping("/pg/{price}")
    public List<Product> getProductsByPriceGreater(@PathVariable Float price){
        return productService.getAllProductsByPriceGreaterThan(price);
    }

    @GetMapping("/pricerange/{price1}-{price2}")
    public List<Product> getProductsByPriceBetween(@PathVariable Float price1, @PathVariable Float price2) {
        return productService.getProductByPriceBetween(price1, price2);
    }

    @GetMapping("/stock/{stock}")
    public List<Product> getProductsByStock(@PathVariable Integer stock) { return productService.getAllProductsByStock(stock);}

    @GetMapping("/stockrange/{stock1}-{stock2}")
    public List<Product> getProductsByStockBetween(@PathVariable Integer stock1, @PathVariable Integer stock2) {
         return productService.getAllProductsByStockBetween( stock1, stock2);
    }

    @GetMapping("/sl/{stock}")
    public List<Product> getProductsByStockLess(@PathVariable Integer stock){
        return productService.getAllProductsByStockLessThan(stock);
    }

    @GetMapping("/sg/{stock}")
    public List<Product> getProductsByStockGreater(@PathVariable Integer stock){
        return productService.getAllProductsByStockGreaterThan(stock);
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
