package com.ecommercebackend.ecommercebackend.db.repo;


import com.ecommercebackend.ecommercebackend.db.entity.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findByName(String name);
}