package com.ecommercebackend.ecommercebackend.service.interfaces;

import com.ecommercebackend.ecommercebackend.pojo.request.SignupRequest;
import com.ecommercebackend.ecommercebackend.pojo.response.SignupResponse;

public interface SignupInterface {
    SignupResponse signup(SignupRequest request, String type);
}
