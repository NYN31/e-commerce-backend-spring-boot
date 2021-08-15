package com.ecommercebackend.ecommercebackend.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_ratings")
public class ProductRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id ;
    @Column(name = "product_id")
    public Integer product_id;
    @Column(name = "user_id")
    public Integer user_id;
    @Column(name = "rating")
    public Double rating;
}
