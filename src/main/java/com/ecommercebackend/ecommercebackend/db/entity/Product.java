package com.ecommercebackend.ecommercebackend.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id ;
    @Column(name = "seller_id")
    public Integer sellerId;
    @Column(name = "name")
    public String name;
    @Column(name = "tag")
    public String tag;
    @Column(name = "price")
    public Integer price ;
    @Column(name = "quantity")
    public Integer quantity ;
    @Column(name = "rating")
    public Double rating ;
}
