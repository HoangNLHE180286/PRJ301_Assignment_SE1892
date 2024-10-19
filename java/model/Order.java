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
public class Order {
    // attributes / fields
    public String orderID;
    public Date orderDate;
    public Date requiredDate;
    public double freight;
    public Payment paymentID;    
    
    // constructor
    public Order() {
    }

    public Order(String orderID, Date orderDate, Date requiredDate, double freight, Payment paymentID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.freight = freight;
        this.paymentID = paymentID;
    }
    
    // getters
    public String getOrderID() {
        return orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public double getFreight() {
        return freight;
    }

    public Payment getPaymentID() {
        return paymentID;
    }
    
    // setters
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public void setPaymentID(Payment paymentID) {
        this.paymentID = paymentID;
    }
    
    // others

}
