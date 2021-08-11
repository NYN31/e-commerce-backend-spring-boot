package com.ecommercebackend.ecommercebackend.controller;

import com.ecommercebackend.ecommercebackend.entities.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HomeController {
    private List<BuyerRegDetails> buyerList = new ArrayList<>() ;
    private List<SellerRegDetails> sellerList = new ArrayList<>() ;


    // welcome page
    @GetMapping("/")
    public String index() {
        return "Welcome to my page"  ;
    }

    // adding buyers
    @PostMapping("/addbuyer")
    public List<BuyerRegDetails> addBuyer(@RequestBody BuyerRegDetails buyer) {
        buyerList.add(buyer) ;
        return buyerList ;
    }

    // adding sellers
    @PostMapping("/addseller")
    public List<SellerRegDetails> addBuyer(@RequestBody SellerRegDetails seller) {
        sellerList.add(seller) ;
        return sellerList ;
    }



    // getting all buyers
    @GetMapping("/allbuyers")
    public List<BuyerRegDetails> getAllBuyers() {
        buyerList = allBuyers() ;
        return buyerList ;
    }
    // getting all sellers
    @GetMapping("/allsellers")
    public List<SellerRegDetails> getAllSellers() {
        sellerList = allSellers() ;
        return sellerList ;
    }


    private List<BuyerRegDetails> allBuyers() {
        buyerList.add(new BuyerRegDetails(1, "samin", "samin@gmail.com", "12abAB#",
                "Dhaka, Bangladesh", 500000.0, "brack", "12347"));
        return buyerList ;
    }
    private List<SellerRegDetails> allSellers() {
        sellerList.add(new SellerRegDetails(1, "seller1", "seller1@gmail.com", "12abAB#",
                "xyz1", "Dhaka, Bangladesh", 500000.0, "dutch bangla", "34567"));
        return sellerList ;
    }


}
