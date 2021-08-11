package com.ecommercebackend.ecommercebackend.controller;

import com.ecommercebackend.ecommercebackend.db.entity.File;
import com.ecommercebackend.ecommercebackend.db.repo.FileRepository;
import com.ecommercebackend.ecommercebackend.entities.BuyerRegDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class HomeController {
    private List<BuyerRegDetails> buyers = new ArrayList<>() ;
    @Autowired
    FileRepository fileRepository;

    @GetMapping("/")
    public String index() {
        return "Welcome to my page"  ;
    }

    @GetMapping("/buyers")
    public List<BuyerRegDetails> getAllBuyers() {
        buyers = allBuyers() ;
        return buyers;
    }

    @PostMapping("/buyers")
    public List<BuyerRegDetails> addBuyer(@RequestBody BuyerRegDetails buyer) {
        buyers.add(buyer) ;
        return buyers ;
    }
    private List<BuyerRegDetails> allBuyers() {
        buyers.add(new BuyerRegDetails(1, "noyon", "noyon@gmail.com")) ;
        buyers.add(new BuyerRegDetails(2, "rakib", "rakib@gmail.com")) ;
        buyers.add(new BuyerRegDetails(3, "samin", "samin@gmail.com")) ;
        return buyers ;
    }

    @GetMapping("/db")
    public List<File> getResult(){
        List<File> files = (List<File>) fileRepository.findAll();
        return files;
    }
}