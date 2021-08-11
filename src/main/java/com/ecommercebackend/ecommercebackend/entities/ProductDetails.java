package com.ecommercebackend.ecommercebackend.entities;

public class ProductDetails {
    private int ProductId;
    private int SellerId;
    private String ProductName ;
    private String ProductTag ;
    private int Price ;
    private String ProductImagePath = "";
    private int Quantity ;
    private double Rating ;

    public ProductDetails(int productId, int sellerId, String name,
                          String tag, int price, int quantity, double rating) {
        ProductId = productId ;
        SellerId = sellerId;
        ProductName = name ;
        ProductTag = tag ;
        Price = price ;
        Quantity = quantity ;
        Rating = rating;
    }

    public int getProductId() { return ProductId; }
    public int getSellerId() { return SellerId; }
    public String getProductName() { return ProductName; }
    public String getProductTag() { return ProductTag; }
    public int getProductPrice() { return Price; }
    public int getProductQuantity() { return Quantity; }
    public String getProductImagePath() { return ProductImagePath; }
    public double getRating() { return Rating; }
}
