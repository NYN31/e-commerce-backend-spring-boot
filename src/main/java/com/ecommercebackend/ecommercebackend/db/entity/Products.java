package com.ecommercebackend.ecommercebackend.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Products {
    @Id
    @Column(name = "id")
    public Integer id ;
    @Column(name = "seller_id")
    public Integer seller_id;
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
