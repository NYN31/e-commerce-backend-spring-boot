package com.ecommercebackend.ecommercebackend.db.repo;


import com.ecommercebackend.ecommercebackend.db.entity.Products;
import org.springframework.data.repository.CrudRepository;


public interface ProductsRepository extends CrudRepository<Products, Integer> {

}