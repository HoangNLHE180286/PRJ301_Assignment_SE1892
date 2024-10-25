/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Payment {
    // attributes / fields
    public String orderID;
    public String paymentMethod;
    public Date paymentDate;
    public double price;
    
    // constructor
    public Payment() {
    }

    public Payment(String orderID, String paymentMethod, Date paymentDate, double price) {
        this.orderID = orderID;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.price = price;
    }
    
    // getters

    public String getOrderID() {
        return orderID;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public double getPrice() {
        return price;
    }
    
    // setters

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    // others

}
