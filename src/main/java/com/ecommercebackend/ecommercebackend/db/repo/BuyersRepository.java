package com.ecommercebackend.ecommercebackend.db.repo;


import com.ecommercebackend.ecommercebackend.db.entity.Buyers;
import org.springframework.data.repository.CrudRepository;


public interface BuyersRepository extends CrudRepository<Buyers, Integer> {
    Buyers findByEmail(String email);
}

