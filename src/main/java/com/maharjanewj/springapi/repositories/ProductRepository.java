package com.maharjanewj.springapi.repositories;

import com.maharjanewj.springapi.models.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Products, String> {

    @Override
    void delete(Products deleted);
}
