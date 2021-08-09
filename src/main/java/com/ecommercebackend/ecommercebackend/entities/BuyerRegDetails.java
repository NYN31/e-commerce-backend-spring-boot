package com.ecommercebackend.ecommercebackend.entities;

public class BuyerRegDetails {
    public int id ;
    public String name ;
    public String email ;

    public  BuyerRegDetails(int id, String name, String email) {
        this.id = id ;
        this.name = name ;
        this.email = email ;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
