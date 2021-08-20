package com.ecommercebackend.ecommercebackend.controller;


import com.ecommercebackend.ecommercebackend.db.entity.*;
import com.ecommercebackend.ecommercebackend.db.repo.*;
import com.ecommercebackend.ecommercebackend.pojo.request.SignInRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.SignOutRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.SignupRequest;
import com.ecommercebackend.ecommercebackend.pojo.response.SignupResponse;
import com.ecommercebackend.ecommercebackend.service.impl.SignInOutService;
import com.ecommercebackend.ecommercebackend.service.impl.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HomeController {
    /*
        1. Add buyers
        2. Add sellers
        3. Get all users
    */
    @Autowired
    SignupService signupService;
    @Autowired
    UsersRepository usersRepository ;
    @Autowired
    SignInOutService signInOutService;

    // welcome page
    @GetMapping("/")
    public String index() {
        return "index changed";
    }


    /**** BUYERS & SELLER PARTS ***/

    // 1. adding buyers
    @PostMapping(
            value = "/buyer/register",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SignupResponse addBuyer(@RequestBody SignupRequest request) throws Exception{
        return signupService.signup(request, "buyer");
    }

    // 2. adding sellers
    @PostMapping(
            value = "/seller/register",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SignupResponse addSeller(@RequestBody SignupRequest request) throws Exception {
        return signupService.signup(request, "seller");
    }


    // 3. sign in
    @PostMapping(
            value = "/sign-in",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String signIn(@RequestBody SignInRequest request) throws Exception{
        return signInOutService.signIn(request);
    }
    // 4. sign out
    @PutMapping(
            value = "/sign-out",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String signOut(@RequestBody SignOutRequest request) throws Exception{
        return signInOutService.signOut(request);
    }

}
