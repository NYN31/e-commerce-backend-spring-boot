package com.ecommercebackend.ecommercebackend.db.repo;


import com.ecommercebackend.ecommercebackend.db.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UsersRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    User findByName(String name);
    User findById(int id);
}

