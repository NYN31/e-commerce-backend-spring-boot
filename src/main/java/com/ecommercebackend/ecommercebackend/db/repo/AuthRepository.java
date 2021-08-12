package com.ecommercebackend.ecommercebackend.db.repo;


import com.ecommercebackend.ecommercebackend.db.entity.Auth;
import org.springframework.data.repository.CrudRepository;


public interface AuthRepository extends CrudRepository<Auth, Integer> {

}