/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Product {

    // attributes / fields
    public int productID;
    public String productName;
    public Category categoryID;
    public int unitInStock;
    public String images;
    
    // constructor
    public Product() {
    }

    public Product(int productID, String productName, Category categoryID, int unitInStock, String images) {
        this.productID = productID;
        this.productName = productName;
        this.categoryID = categoryID;
        this.unitInStock = unitInStock;
        this.images = images;
    }

    // getters
    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public int getUnitInStock() {
        return unitInStock;
    }

    public String getImages() {
        return images;
    }
    
    // setters
    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    public void setUnitInStock(int unitInStock) {
        this.unitInStock = unitInStock;
    }
    
    public void setImages(String images) {
        this.images = images;
    }
	
    // others
}
