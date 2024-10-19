/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 *
 * @author Admin
 */
public class OrderDetail {
    // attributes / fields    
    public Order orderID;    
    public Product productID;    
    public int quantity;    
    public double unitPrice;    
    public double discount;
    
    // constructor
    public OrderDetail() {
    }

    public OrderDetail(Order orderID, Product productID, int quantity, double unitPrice, double discount) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }
    
    // getters
    public Order getOrderID() {
        return orderID;
    }

    public Product getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getDiscount() {
        return discount;
    }
    
    // setters
    public void setOrderID(Order orderID) {
        this.orderID = orderID;
    }

    public void setProductID(Product productID) {
        this.productID = productID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    
    // others

}
