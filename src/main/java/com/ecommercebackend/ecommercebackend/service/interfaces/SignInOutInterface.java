package com.ecommercebackend.ecommercebackend.service.interfaces;

import com.ecommercebackend.ecommercebackend.db.entity.Auth;
import com.ecommercebackend.ecommercebackend.pojo.request.SignInRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.SignOutRequest;

public interface SignInOutInterface {
    String signIn(SignInRequest request) throws Exception;
    String signOut() throws Exception;
}
