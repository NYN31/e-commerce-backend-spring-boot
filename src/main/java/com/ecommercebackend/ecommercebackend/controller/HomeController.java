package com.ecommercebackend.ecommercebackend.controller;


import com.ecommercebackend.ecommercebackend.db.entity.*;
import com.ecommercebackend.ecommercebackend.db.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class HomeController {

    @Autowired
    FileRepository fileRepository;
    @Autowired
    BuyersRepository buyersRepository ;
    @Autowired
    SellersRepository sellersRepository ;

    // welcome page
    @GetMapping("/")
    public String index() {
        return "Welcome to my page"  ;
    }

    // adding buyers
    @PostMapping("/db/buyers")
    public Buyers addBuyer(@RequestBody Buyers buyer) {
        buyersRepository.save(buyer) ;
        return buyer ;
    }
    // getting all buyers
    @GetMapping("/db/buyers")
    public List<Buyers> getAllBuyers(){
        List<Buyers> buyers = (List<Buyers>) buyersRepository.findAll();
        return buyers;
    }

    // adding sellers
    @PostMapping("/db/sellers")
    public Sellers addBuyer(@RequestBody Sellers seller) {
        sellersRepository.save(seller) ;
        return seller ;
    }
    // getting all sellers
    @GetMapping("/db/sellers")
    public List<Sellers> getAllSellers(){
        List<Sellers> sellers = (List<Sellers>) sellersRepository.findAll();
        return sellers;
    }
}
