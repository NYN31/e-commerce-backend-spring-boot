package com.ecommercebackend.ecommercebackend.service.impl;

import com.ecommercebackend.ecommercebackend.db.entity.Auth;
import com.ecommercebackend.ecommercebackend.db.entity.User;
import com.ecommercebackend.ecommercebackend.db.repo.AuthRepository;
import com.ecommercebackend.ecommercebackend.db.repo.UsersRepository;
import com.ecommercebackend.ecommercebackend.service.interfaces.SignInInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Service
public class SignInService implements SignInInterface {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    AuthRepository authRepository;

    public String signIn(String email, String password) throws Exception{
        User user = usersRepository.findByEmail(email);
        if(user == null) {
            throw new Exception("Invalid Credential");
        }
        if(!user.password.equals(password)) {
            throw new Exception("Wrong password");
        }
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByUserId(user.id.intValue()));
        if(auth.isPresent() && auth.get().isActive) {
            return auth.get().token;
        }
        

        Auth authen = new Auth();
        authen.userId = user.id;
        authen.isActive = true;
        authen.token = Base64.getEncoder().encodeToString(email.getBytes());
        authRepository.save(authen);
        return authen.token;
    }

    public Boolean isSignIn(String token) throws Exception {
        Auth auth = authRepository.findByToken(token);
        if(auth == null || auth.isActive == false) {
            throw new Exception("Please log in first");
        } else {
            return true;
        }
    }
}
