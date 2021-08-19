package com.ecommercebackend.ecommercebackend.service.interfaces;

import com.ecommercebackend.ecommercebackend.db.entity.Auth;

public interface SignInInterface {
    String signIn(String email, String password) throws Exception;
}
