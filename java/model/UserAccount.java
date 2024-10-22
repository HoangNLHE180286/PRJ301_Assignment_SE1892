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
public class UserAccount {

    // attributes / fields
    public int userID;
    public String username;
    public String password;
    public String role;
    public String email;
    public Date createDate;
    public String phone;
    
    // constructor
    public UserAccount() {
    }

    public UserAccount(int userID, String username, String password, String email, Date createDate, String phone) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.phone = phone;
        this.role = "User";
    }

    // getters
    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }
            
    // setters
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
	
    // others
}
