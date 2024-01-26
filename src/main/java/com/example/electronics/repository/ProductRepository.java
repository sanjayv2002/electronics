package com.example.electronics.repository;

import com.example.electronics.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

//    @Query("{price: ?lt ?0}")
//    List<Product> findAllByPrice(Float price1, Float price2);
}
