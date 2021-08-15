package com.ecommercebackend.ecommercebackend.db.repo;


import com.ecommercebackend.ecommercebackend.db.entity.ProductRating;
import org.springframework.data.repository.CrudRepository;


public interface ProductRatingsRepository extends CrudRepository<ProductRating, Integer> {
    ProductRating findById(int user_id);
}