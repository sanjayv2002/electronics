package com.example.electronics.controller;

import com.example.electronics.model.Product;
import com.example.electronics.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/products")
@Tag(name = "Product Management API", description = "Endpoints for managing products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @Operation(summary = "Create a new Product", description = "Returns the added product Object as a response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "500", description = "Could not create the product due to some unknown error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createTask(
            @RequestBody Product product
    ) {
        try {
            Product addedProduct = productService.addProduct(product);
            log.info("new product {} created",addedProduct.getProductId());
            return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Unable to create product");
            return new ResponseEntity<>("Unable to create product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Returns a list of all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched products"),
            @ApiResponse(responseCode = "404", description = "No products found")
    })
    public ResponseEntity<?> getProducts() {
        try {
            List<Product> allProducts = productService.findAllProducts();
            log.info("All products fetched successfully");
            return new ResponseEntity<>(allProducts,HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all products", e);
            return new ResponseEntity<>("I am not sure but this should work", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get product by ID", description = "Returns the product with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched product by ID"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<?> getProduct(@PathVariable String productId) {
        try {
            Product product = productService.getProductById(productId);
            log.info("Product fetched by ID: {}", productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching product by ID: {}", productId, e);
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/price/{price}")
    @Operation(summary = "Get products by price", description = "Returns a list of products with the specified price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched products by price"),
            @ApiResponse(responseCode = "404", description = "No product found for that price")
    })
    public ResponseEntity<?> getProductsByPrice(@PathVariable Float price) {
        try {
            List<Product> allProductsByPrice = productService.getAllProductsByPrice(price);
            log.info("Products fetched by price: {}", price);
            return new ResponseEntity<>(allProductsByPrice, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching products by price: {}", price, e);
            return new ResponseEntity<>("No product found for that price", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pl/{price}")
    @Operation(summary = "Get products by price less than", description = "Returns a list of products with a price less than the specified value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched products by price less than"),
            @ApiResponse(responseCode = "404", description = "No product less than this price is found")
    })
    public ResponseEntity<?> getProductsByPriceLess(@PathVariable Float price) {
        try {
            List<Product> allProductsByPriceLessThan = productService.getAllProductsByPriceLessThan(price);
            log.info("Products fetched by price less than: {}", price);
            return new ResponseEntity<>(allProductsByPriceLessThan, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching products by price less than: {}", price, e);
            return new ResponseEntity<>("No product less than this price is found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pg/{price}")
    @Operation(summary = "Get products by price greater than", description = "Returns a list of products with a price greater than the specified value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched products by price greater than"),
            @ApiResponse(responseCode = "404", description = "No product greater than this price is found")
    })
    public ResponseEntity<?> getProductsByPriceGreater(@PathVariable Float price) {
        try {
            List<Product> allProductsByPriceGreaterThan = productService.getAllProductsByPriceGreaterThan(price);
            log.info("Products fetched by price greater than: {}", price);
            return new ResponseEntity<>(allProductsByPriceGreaterThan, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching products by price greater than: {}", price, e);
            return new ResponseEntity<>("No product greater than this price is found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pricerange/{price1}-{price2}")
    @Operation(summary = "Get products by price range", description = "Returns a list of products with prices between the specified range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched products by price range"),
            @ApiResponse(responseCode = "404", description = "No product between the given price range is found")
    })
    public ResponseEntity<?> getProductsByPriceBetween(@PathVariable Float price1, @PathVariable Float price2) {
        try {
            List<Product> productByPriceBetween = productService.getProductByPriceBetween(price1, price2);
            log.info("Products fetched by price between: {} and {}", price1, price2);
            return new ResponseEntity<>(productByPriceBetween, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching products by price between: {} and {}", price1, price2, e);
            return new ResponseEntity<>("No product between the given price is found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/stock/{stock}")
    @Operation(summary = "Get products by stock", description = "Returns a list of products with the specified stock value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched products by stock"),
            @ApiResponse(responseCode = "404", description = "No product found for this stock")
    })
    public ResponseEntity<?> getProductsByStock(@PathVariable Integer stock) {
        try {
            List<Product> allProductsByStock = productService.getAllProductsByStock(stock);
            log.info("Products fetched by stock: {}", stock);
            return new ResponseEntity<>(allProductsByStock, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching products by stock: {}", stock, e);
            return new ResponseEntity<>("No product found for this stock", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/stockrange/{stock1}-{stock2}")
    @Operation(summary = "Get products by stock range", description = "Returns a list of products with stock values between the specified range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched products by stock range"),
            @ApiResponse(responseCode = "404", description = "No product between the given stock range is found")
    })
    public ResponseEntity<?> getProductsByStockBetween(@PathVariable Integer stock1, @PathVariable Integer stock2) {
        try {
            List<Product> allProductsByStockBetween = productService.getAllProductsByStockBetween(stock1, stock2);
            log.info("Products fetched by stock between: {} and {}", stock1, stock2);
            return new ResponseEntity<>(allProductsByStockBetween, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching products by stock between: {} and {}", stock1, stock2, e);
            return new ResponseEntity<>("No product between the given stocks is found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/sl/{stock}")
    @Operation(summary = "Get products by stock less than", description = "Returns a list of products with a stock value less than the specified value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched products by stock less than"),
            @ApiResponse(responseCode = "404", description = "No product less than this stock value is found")
    })
    public ResponseEntity<?> getProductsByStockLess(@PathVariable Integer stock) {
        try {
            List<Product> allProductsByStockLessThan = productService.getAllProductsByStockLessThan(stock);
            log.info("Products fetched by stock less than: {}", stock);
            return new ResponseEntity<>(allProductsByStockLessThan, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching products by stock less than: {}", stock, e);
            return new ResponseEntity<>("No product less than this stock value is found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/sg/{stock}")
    @Operation(summary = "Get products by stock greater than", description = "Returns a list of products with a stock value greater than the specified value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched products by stock greater than"),
            @ApiResponse(responseCode = "404", description = "No product greater than this stock value is found")
    })
    public ResponseEntity<?> getProductsByStockGreater(@PathVariable Integer stock) {
        try {
            List<Product> allProductsByStockGreaterThan = productService.getAllProductsByStockGreaterThan(stock);
            log.info("Products fetched by stock greater than: {}", stock);
            return new ResponseEntity<>(allProductsByStockGreaterThan, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching products by stock greater than: {}", stock, e);
            return new ResponseEntity<>("No product greater than this stock value is found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    @Operation(summary = "Update a product", description = "Returns the updated product Object as a response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated product"),
            @ApiResponse(responseCode = "500", description = "Error updating product")
    })
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        try {
            Product updatedProduct = productService.updateProduct(product);
            log.info("Product updated: {}", updatedProduct);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating product", e);
            return new ResponseEntity<>("Error updating product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Delete a product by ID", description = "Returns a message indicating the deletion status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted product by ID"),
            @ApiResponse(responseCode = "500", description = "Error deleting product")
    })
    public ResponseEntity<?> deleteProduct(@PathVariable String productId) {
        try {
            String deleted = productService.deleteProduct(productId);
            log.info("Product deleted with ID: {}", productId);
            return new ResponseEntity<>(deleted, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting product with ID: {}", productId, e);
            return new ResponseEntity<>("Error deleting product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
