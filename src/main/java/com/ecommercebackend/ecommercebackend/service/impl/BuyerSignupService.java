package com.ecommercebackend.ecommercebackend.service.impl;

import com.ecommercebackend.ecommercebackend.db.entity.Buyers;
import com.ecommercebackend.ecommercebackend.db.repo.BuyersRepository;
import com.ecommercebackend.ecommercebackend.pojo.request.SignupRequest;
import com.ecommercebackend.ecommercebackend.pojo.response.SignupResponse;
import com.ecommercebackend.ecommercebackend.service.interfaces.SignupInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerSignupService implements SignupInterface {
    @Autowired
    BuyersRepository buyersRepository;

    @Override
    public SignupResponse signup(SignupRequest request) {
        System.out.println("Received buyer signup request");
        Buyers buyer = buyersRepository.findByEmail(request.email);
        System.out.println("Fetched data from database");
        if(buyer == null){

            buyer = new Buyers();
            buyer.email = request.email;
            buyer.address = request.address;
            buyer.name = request.name;
            buyer.password = request.password;
            System.out.println("Save to db");
            buyersRepository.save(buyer);
            System.out.println("Data saved!");
            SignupResponse response = new SignupResponse();
            response.statusCode = 200;
            response.message = "Signup successful";
            return response;
        } else {
            SignupResponse response = new SignupResponse();
            response.statusCode = 425;
            response.message = "Buyer already exist.";
            return response;
        }
    }
}
