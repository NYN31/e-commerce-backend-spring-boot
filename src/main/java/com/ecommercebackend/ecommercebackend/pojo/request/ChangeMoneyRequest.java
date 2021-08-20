package com.ecommercebackend.ecommercebackend.pojo.request;

import com.ecommercebackend.ecommercebackend.pojo.response.CommonResponse;

public class ChangeMoneyRequest {
    public String token;
    public String email; // will be deleted after add and withdraw money
    public String bankName;
    public String bankBranch;
    public Double balance;
}

/***
 {
    "token": "",
    "bankName": "",
    "bankBranch": "",
    "balance": 500.0;
 }
 * ***/
