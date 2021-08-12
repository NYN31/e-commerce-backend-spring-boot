package com.ecommercebackend.ecommercebackend.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductRatings {
    @Id
    @Column(name = "id")
    public Integer id ;
    @Column(name = "product_id")
    public Integer product_id;
    @Column(name = "buyer_id")
    public Integer buyer_id;
    @Column(name = "ratings")
    public Double ratings;
}
