package com.ecommercebackend.ecommercebackend.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id ;
    @Column(name = "name")
    public String name ;
    @Column(name = "email")
    public String email ;
    @Column(name = "password")
    public String password ;
    @Column(name = "type")
    public String type ;
    @Column(name = "company")
    public String company ;
    @Column(name = "address")
    public String address ;
    @Column(name = "imageUrl")
    public String imageUrl ;
    @Column(name = "balance")
    public Double balance ;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", balance=" + balance +
                '}';
    }
}
