/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 *
 * @author Admin
 */
public class Review {
    // attributes / fields    
    public UserAccount userID;
    public Product productID;
    public float rating;
    public String review;
    
    // constructor
    public Review() {
    }

    public Review(UserAccount userID, Product productID, float rating, String review) {
        this.userID = userID;
        this.productID = productID;
        this.rating = rating;
        this.review = review;
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

    public String getReview() {
        return review;
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

    public void setReview(String review) {
        this.review = review;
    }
    
    // others

}
