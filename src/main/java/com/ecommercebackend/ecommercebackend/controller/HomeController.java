package com.ecommercebackend.ecommercebackend.controller;

<<<<<<< HEAD
import com.ecommercebackend.ecommercebackend.entities.*;
import org.springframework.web.bind.annotation.*;

=======
import com.ecommercebackend.ecommercebackend.db.entity.File;
import com.ecommercebackend.ecommercebackend.db.repo.FileRepository;
import com.ecommercebackend.ecommercebackend.entities.BuyerRegDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> ba67a2f2751111ffbb6657501814a2e6ec8dbaf1
import java.util.*;

@RestController
public class HomeController {
<<<<<<< HEAD
    private List<BuyerRegDetails> buyerList = new ArrayList<>() ;
    private List<SellerRegDetails> sellerList = new ArrayList<>() ;

=======
    private List<BuyerRegDetails> buyers = new ArrayList<>() ;
    @Autowired
    FileRepository fileRepository;
>>>>>>> ba67a2f2751111ffbb6657501814a2e6ec8dbaf1

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

<<<<<<< HEAD

}
=======
    @GetMapping("/db")
    public List<File> getResult(){
        List<File> files = (List<File>) fileRepository.findAll();
        return files;
    }
}
>>>>>>> ba67a2f2751111ffbb6657501814a2e6ec8dbaf1
