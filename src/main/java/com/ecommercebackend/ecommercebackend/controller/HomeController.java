package com.ecommercebackend.ecommercebackend.controller;


import com.ecommercebackend.ecommercebackend.db.entity.*;
import com.ecommercebackend.ecommercebackend.db.repo.*;
import com.ecommercebackend.ecommercebackend.pojo.request.SignupRequest;
import com.ecommercebackend.ecommercebackend.pojo.response.SignupResponse;
import com.ecommercebackend.ecommercebackend.service.impl.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HomeController {
    @Autowired
    SignupService signupService;
    @Autowired
    UsersRepository usersRepository ;

    // welcome page
    @GetMapping("/")
    public String index() {
        return "index";
    }


    /**** BUYERS & SELLER PARTS ***/

    // adding buyers
    @PostMapping(
            value = "/buyer/register",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SignupResponse addBuyer(@RequestBody SignupRequest request) {
        return signupService.signup(request, "buyer");
    }

    // adding sellers
    @PostMapping(
            value = "/seller/register",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SignupResponse addSeller(@RequestBody SignupRequest request) {
        return signupService.signup(request, "seller");
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        return (List<User>) usersRepository.findAll() ;
    }


}
