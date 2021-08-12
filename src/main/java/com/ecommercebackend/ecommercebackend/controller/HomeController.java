package com.ecommercebackend.ecommercebackend.controller;


import com.ecommercebackend.ecommercebackend.db.entity.*;
import com.ecommercebackend.ecommercebackend.db.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HomeController {
    @Autowired
    BuyersRepository buyersRepository ;
    @Autowired
    SellersRepository sellersRepository ;
    @Autowired
    ProductsRepository productsRepository ;
    @Autowired
    ProductRatingsRepository productRatingsRepository ;
    @Autowired
    ProductPurchaseRepository productPurchaseRepository ;

    /*
            System.out.println("All Buyer feature...!") ;
			System.out.println("1. Edit profile"); // done, without picture db
			System.out.println("2. View products"); // done
			System.out.println("3. Buy a product"); // done
			System.out.println("4. Rate a product"); // done
			System.out.println("5. Check purchase history"); // done
			System.out.println("6. Verify or Connect bank account"); // done
			System.out.println("7. Add money"); // done
			System.out.println("8. Sign out"); // done

    */

    // welcome page
    @GetMapping("/")
    public String index() {
        return "Welcome to my page"  ;
    }


    /**** BUYERS & SELLER PARTS ***/

    // adding buyers
    @PostMapping("/buyer/register")
    public Buyers addBuyer(@RequestBody Buyers buyer) {
        buyersRepository.save(buyer) ;
        return buyer ;
    }
    // getting all buyers
    @GetMapping("/buyers")
    public List<Buyers> getAllBuyers(){
        List<Buyers> buyers = (List<Buyers>) buyersRepository.findAll();
        return buyers;
    }
    // updating buyers
    @PutMapping("/buyer/update")
    public List<Buyers> updateBuyer(@RequestBody Buyers buyer) {
        buyersRepository.save(buyer) ;
        List<Buyers> buyers = (List<Buyers>) buyersRepository.findAll() ;
        return buyers ;
    }
    // adding sellers
    @PostMapping("/seller/register")
    public Sellers addBuyer(@RequestBody Sellers seller) {
        sellersRepository.save(seller) ;
        return seller ;
    }
    // getting all sellers
    @GetMapping("/sellers")
    public List<Sellers> getAllSellers(){
        List<Sellers> sellers = (List<Sellers>) sellersRepository.findAll();
        return sellers;
    }



    /*** PRODUCTS PARTS ***/

    // getting all products
    @GetMapping("/products")
    public List<Products> getAllProducts() {
        return (List<Products>) productsRepository.findAll() ;
    }
    // adding product
    @PostMapping("/products/add")
    public List<Products> addProduct(@RequestBody Products product) {
        productsRepository.save(product) ;
        return (List<Products>) productsRepository.findAll() ;
    }
    // updating product
    @PutMapping("/product/update")
    public List<Products> updateProduct(@RequestBody Products product) {
        productsRepository.save(product) ;
        return (List<Products>) productsRepository.findAll() ;
    }
    @DeleteMapping("/product/delete/{Id}")
    public List<Products> deleteProduct(@PathVariable String Id) {
        Integer id = Integer.parseInt(Id) ;
        productsRepository.deleteById(id);
        return (List<Products>) productsRepository.findAll() ;
    }

    /*** PRODUCT RATINGS ***/
    // get all rating
    @GetMapping("/product-ratings")
    public List<ProductRatings> getAllProductRatings() {
        return (List<ProductRatings>) productRatingsRepository.findAll() ;
    }
    // add rating
    @PostMapping("/product-ratings/add")
    public List<ProductRatings> addProductRatings(@RequestBody ProductRatings ratings) {
        productRatingsRepository.save(ratings) ;
        return (List<ProductRatings>) productRatingsRepository.findAll() ;
    }

    /*** CHECK PRODUCT PURCHASE HISTORY***/

    // get purchases list
    @GetMapping("/purchases")
    public List<ProductPurchase> getAllPurchase() {
        return (List<ProductPurchase>) productPurchaseRepository.findAll() ;
    }
    @PostMapping("/purchases/add")
    public List<ProductPurchase> addPurchase(@RequestBody ProductPurchase purchase) {
        productPurchaseRepository.save(purchase) ;
        return (List<ProductPurchase>) productPurchaseRepository.findAll() ;
    }
}
