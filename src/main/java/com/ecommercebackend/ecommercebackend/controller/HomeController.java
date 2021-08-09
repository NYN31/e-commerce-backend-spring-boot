package com.ecommercebackend.ecommercebackend.controller;

import com.ecommercebackend.ecommercebackend.entities.BuyerRegDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class HomeController {
    private List<BuyerRegDetails> buyers = new ArrayList<>() ;
    @GetMapping("/")
    public String index() {
        return "Welcome to my page"  ;
    }

    @GetMapping("/buyers")
    public List<BuyerRegDetails> getAllBuyers() {
        List<BuyerRegDetails> buyers = allBuyers() ;
        return buyers;
    }

    private List<BuyerRegDetails> allBuyers() {
        List<BuyerRegDetails> list = null;
        list.add(new BuyerRegDetails(1, "noyon", "noyon@gmail.com")) ;
        list.add(new BuyerRegDetails(2, "rakib", "rakib@gmail.com")) ;
        list.add(new BuyerRegDetails(3, "samin", "samin@gmail.com")) ;
        return list ;
    }
}
