package com.ecommercebackend.ecommercebackend.service.impl;

import com.ecommercebackend.ecommercebackend.db.entity.Auth;
import com.ecommercebackend.ecommercebackend.db.entity.User;
import com.ecommercebackend.ecommercebackend.db.repo.AuthRepository;
import com.ecommercebackend.ecommercebackend.db.repo.UsersRepository;
import com.ecommercebackend.ecommercebackend.pojo.request.SignInRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.SignOutRequest;
import com.ecommercebackend.ecommercebackend.service.interfaces.SignInOutInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class SignInOutService implements SignInOutInterface {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    AuthRepository authRepository;

    public String signIn(SignInRequest request) throws Exception{
        String email = request.email;
        String password = request.password;

        User user = usersRepository.findByEmail(email);
        if(user == null) {
            throw new Exception("Invalid Credential");
        }
        if(!user.password.equals(password)) {
            throw new Exception("Wrong password");
        }
        Auth auth = authRepository.findByUserId(user.id.intValue());
        if(auth != null && auth.isActive) {
            return auth.token;
        }
        if(auth != null && !auth.isActive) {
            auth.isActive = true;
            String tokenString = email + System.currentTimeMillis();
            auth.token = Base64.getEncoder().encodeToString(tokenString.getBytes());
            authRepository.save(auth);
            return auth.token;
        }

        Auth authen = new Auth();
        authen.userId = user.id;
        authen.isActive = true;
        String tokenString = email + System.currentTimeMillis();
        authen.token = Base64.getEncoder().encodeToString(tokenString.getBytes());

        authRepository.save(authen);
        return authen.token;
    }
    public String signOut() throws Exception {
        String token = "lsdf";
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }
        auth.get().isActive = false;
        authRepository.save(auth.get());
        return "Sign out successfully";
    }
}
