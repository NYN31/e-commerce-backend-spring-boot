package com.ecommercebackend.ecommercebackend.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductPurchase {
    @Id
    @Column(name = "id")
    public Integer id ;
    @Column(name = "product_name")
    public String product_name ;
    @Column(name = "product_id")
    public Integer product_id ;
    @Column(name = "buyer_id")
    public Integer buyer_id ;
    @Column(name = "price")
    public Integer price ;
    @Column(name = "quantity")
    public Integer quantity ;
    @Column(name = "seller_id")
    public Integer seller_id ;
}
