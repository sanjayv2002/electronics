package com.example.electronics.repository;

import com.example.electronics.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@EnableMongoRepositories
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {


    @Query("{$and: [{price: {$lte: ?1}}, {price: {$gte: ?0}}]}")
    List<Product> findAllProductsByPriceBetween(Float price1, Float price2);

    @Query("{price: {$lte: ?0}}")
    List<Product> findAllProductsByPriceLessThanEqual(Float price);

    @Query("{price: {$gte: ?0}}")
    List<Product> findAllProductsByPriceGreaterThanEqual(Float price);

    @Query("{stock: {$lte: ?0}}")
    List<Product> findAllProductsByStockLessThanEqual(Integer stock);

    @Query("{stock: {$gte: ?0}}")
    List<Product> findAllProductsByStockGreaterThanEqual(Integer stock);

    @Query("{$and: [{stock: {$lte: ?1}}, {stock: {$gte: ?0}}]}")
    List<Product> findAllProductsByStockBetween(Integer stock1, Integer stock2);

    @Query("{price: {$eq: ?0}}")
    List<Product> findAllProductsByPrice(Float price);

    @Query("{stock: {$eq: ?0}}")
    List<Product> findAllProductsByStock(Integer stock);

}
