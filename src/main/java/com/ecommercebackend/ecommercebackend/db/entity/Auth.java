package com.ecommercebackend.ecommercebackend.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "auths")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id ;
    @Column(name = "userId")
    public Integer userId ;
    @Column(name = "token")
    public String token;
    @Column(name = "isActive")
    public Boolean isActive ;
}


