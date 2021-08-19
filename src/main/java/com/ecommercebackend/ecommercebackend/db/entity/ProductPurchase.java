package com.ecommercebackend.ecommercebackend.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_purchases")
public class ProductPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id ;
    @Column(name = "product_id")
    public Integer product_id ;
    @Column(name = "buyer_id")
    public Integer buyer_id ;
    @Column(name = "purchase_price")
    public Integer purchasePrice ;
    @Column(name = "quantity")
    public Integer quantity ;
}
