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
public class Review {
    // attributes / fields    
    public UserAccount userID;
    public Product productID;
    public float rating;
    public String comment;
    public Date reviewDate;
    
    // constructor
    public Review() {
    }

    public Review(UserAccount userID, Product productID, float rating, String comment, Date reviewDate) {
        this.userID = userID;
        this.productID = productID;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }
    
    // getters
    public UserAccount getUserID() {
        return userID;
    }

    public Product getProductID() {
        return productID;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Date getReviewDate() {
        return reviewDate;
    }
    
    // setters
    public void setUserID(UserAccount userID) {
        this.userID = userID;
    }

    public void setProductID(Product productID) {
        this.productID = productID;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
	
    // others
}
