package com.ecommercebackend.ecommercebackend.service.impl;

import com.ecommercebackend.ecommercebackend.db.entity.Auth;
import com.ecommercebackend.ecommercebackend.db.entity.User;
import com.ecommercebackend.ecommercebackend.db.repo.AuthRepository;
import com.ecommercebackend.ecommercebackend.db.repo.UsersRepository;
import com.ecommercebackend.ecommercebackend.pojo.request.SignupRequest;
import com.ecommercebackend.ecommercebackend.pojo.response.SignupResponse;
import com.ecommercebackend.ecommercebackend.service.interfaces.SignupInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SignupService implements SignupInterface {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    AuthRepository authRepository;

    @Override
    public SignupResponse signup(SignupRequest request, String type) throws Exception {
        User user = usersRepository.findByEmail(request.email);
        if(user == null){
            user = new User();
            user.email = request.email;
            user.address = request.address;
            user.name = request.name;
            user.password = request.password;
            user.company = request.company;
            user.imageUrl = request.imageURL;
            user.type = type ;
            usersRepository.save(user);

            Auth authUser = new Auth();
            authUser.isActive = false;
            authUser.userId = user.id;
            authUser.token = Base64.getEncoder().encodeToString(user.email.getBytes());
            authRepository.save(authUser);

            SignupResponse response = new SignupResponse();
            response.statusCode = 200;
            response.message = "Signup successful";
            return response;
        } else {
            SignupResponse response = new SignupResponse();
            response.statusCode = 425;
            response.message = "user already exist.";
            return response;
        }
    }
}
