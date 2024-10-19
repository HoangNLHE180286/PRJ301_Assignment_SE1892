/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.*;

/**
 *
 * @author Admin
 */
public class UserAccountDAO extends MyDAO {

    public List<UserAccount> getUserAccountList() {
        List<UserAccount> l = new ArrayList<UserAccount>();
        xSql = "select * from UserAccounts";

        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            int xUserID;
            String xUsername;
            String xPassword;
            String xEmail;
            Date xCreateDate;
            String xPhone;
            UserAccount x;

            while (rs.next()) {
                xUserID = rs.getInt("userID");
                xUsername = rs.getString("username");
                xPassword = rs.getString("password");
                xEmail = rs.getString("email");
                xCreateDate = rs.getDate("createDate");
                xPhone = rs.getString("phone");

                x = new UserAccount(xUserID, xUsername, xPassword, xEmail, xCreateDate, xPhone);
                l.add(x);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (l);
    }

    public UserAccount getUserAccountByID(int userID) {
        String xUsername;
        String xPassword;
        String xEmail;
        Date xCreateDate;
        String xPhone;
        UserAccount x = new UserAccount();
        xSql = "select * from UserAccounts where UserID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                xUsername = rs.getString("username");
                xPassword = rs.getString("password");
                xEmail = rs.getString("email");
                xCreateDate = rs.getDate("createDate");
                xPhone = rs.getString("phone");

                x = new UserAccount(userID, xUsername, xPassword, xEmail, xCreateDate, xPhone);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public void insertUserAccount(UserAccount x) {
        xSql = "insert into UserAccounts (UserID, Username, Password, Email, CreatedDate, Phone) values (?, ?, ?, ?, ?, ?)";

        java.sql.Date cd = (java.sql.Date) x.getCreateDate();

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, x.getUserID());
            ps.setString(2, x.getUsername());
            ps.setString(3, x.getPassword());
            ps.setString(4, x.getEmail());
            ps.setDate(5, cd);
            ps.setString(6, x.getPhone());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUserAccount(int userID) {
        xSql = "delete from UserAccounts where UserID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
            
            ps.executeUpdate();
            //con.commit();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUserAccount(int userID, UserAccount x) {
        xSql = "update UserAccounts set Username = ?, Password = ?, Email = ?, CreatedDate = ?, Phone = ? where UserID = ?";
        
        java.sql.Date cd = (java.sql.Date) x.getCreateDate();
        
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getUsername());
            ps.setString(2, x.getPassword());
            ps.setString(3, x.getEmail());
            ps.setDate(4, cd);
            ps.setString(5, x.getPhone());
            
            ps.setInt(6, x.getUserID());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
