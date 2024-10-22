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
    public UserAccount UserID;
    public Date orderDate;
    public Date requiredDate;
    public double freight;  
    
    // constructor
    public Order() {
    }

    public Order(String orderID, UserAccount UserID, Date orderDate, Date requiredDate, double freight) {
        this.orderID = orderID;
        this.UserID = UserID;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.freight = freight;
    }

    
    // getters
    public String getOrderID() {
        return orderID;
    }

    public UserAccount getUserID() {
        return UserID;
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

    // setters
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setUserID(UserAccount UserID) {
        this.UserID = UserID;
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

    // others

}
