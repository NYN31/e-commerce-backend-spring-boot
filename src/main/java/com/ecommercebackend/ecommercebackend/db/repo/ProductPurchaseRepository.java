package com.ecommercebackend.ecommercebackend.db.repo;


import com.ecommercebackend.ecommercebackend.db.entity.Product;
import com.ecommercebackend.ecommercebackend.db.entity.ProductPurchase;
import org.springframework.data.repository.CrudRepository;

import java.util.*;


public interface ProductPurchaseRepository extends CrudRepository<ProductPurchase, Integer> {
    List<ProductPurchase> findById(int id);
}

