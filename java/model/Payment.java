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
    public String paymentID;
    public UserAccount userID;
    public String paymentMethod;
    public Date paymentDate;
    public double price;
    
    // constructor
    public Payment() {
    }

    public Payment(String paymentID, UserAccount userID, String paymentMethod, Date paymentDate, double price) {
        this.paymentID = paymentID;
        this.userID = userID;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.price = price;
    }
    
    // getters
    public String getPaymentID() {
        return paymentID;
    }

    public UserAccount getUserID() {
        return userID;
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
    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public void setUserID(UserAccount userID) {
        this.userID = userID;
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
