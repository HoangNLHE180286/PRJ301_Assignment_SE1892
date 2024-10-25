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

//    public List<UserAccount> getUserAccountList() {
//        List<UserAccount> l = new ArrayList<UserAccount>();
//        xSql = "select * from UserAccounts";
//
//        try {
//            ps = con.prepareStatement(xSql);
//            rs = ps.executeQuery();
//
//            int xUserID;
//            String xUsername;
//            String xPassword;
//            String role;
//            String xEmail;
//            Date xCreateDate;
//            String xPhone;
//            UserAccount x;
//
//            while (rs.next()) {
//                xUserID = rs.getInt("userID");
//                xUsername = rs.getString("username");
//                xPassword = rs.getString("password");
//                xEmail = rs.getString("email");
//                xCreateDate = rs.getDate("createDate");
//                xPhone = rs.getString("phone");
//
//                x = new UserAccount(xUserID, xUsername, xPassword, xEmail, xCreateDate, xPhone);
//                l.add(x);
//            }
//
//            rs.close();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return (l);
//    }
    
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
                xUsername = rs.getString("Username");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xCreateDate = rs.getDate("CreateDate");
                xPhone = rs.getString("Phone");

                x = new UserAccount(userID, xUsername, xPassword, xEmail, xCreateDate, xPhone);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public UserAccount getUserAccountByUsernamePassword(String username, String password) {
        int xId;
        String xEmail;
        Date xCreateDate;
        String xPhone;

        UserAccount x = new UserAccount();
        String xSql = "SELECT * FROM UserAccounts WHERE Username = ? and Password = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next()) {
                xId = rs.getInt("UserID");
                xEmail = rs.getString("Email");
                xCreateDate = rs.getDate("CreatedDate");
                xPhone = rs.getString("Phone");

                x = new UserAccount(xId, username, password, xEmail, xCreateDate, xPhone);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public UserAccount getUserAccountByUsername(String username) {
        int xId;
        String xPassword;
        String xEmail;
        Date xCreateDate;
        String xPhone;

        UserAccount x = new UserAccount();
        String xSql = "SELECT * FROM UserAccounts WHERE Username = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);

            rs = ps.executeQuery();
            if (rs.next()) {
                xId = rs.getInt("UserID");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xCreateDate = rs.getDate("CreatedDate");
                xPhone = rs.getString("Phone");

                x = new UserAccount(xId, username, xPassword, xEmail, xCreateDate, xPhone);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public void updatePassword(String username, String newPassword) {
        String xSql = "UPDATE UserAccounts SET Password = ? WHERE Username = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, newPassword);
            ps.setString(2, username);

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertUserAccount(UserAccount x) {
        xSql = "insert into UserAccounts (UserID, Username, Password, Role, Email, CreatedDate, Phone) values (?, ?, ?, ?, ?, ?)";

        java.sql.Date cd = (java.sql.Date) x.getCreateDate();

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, x.getUserID());
            ps.setString(2, x.getUsername());
            ps.setString(3, x.getPassword());
            ps.setString(4, x.getRole());
            ps.setString(5, x.getEmail());
            ps.setDate(6, cd);
            ps.setString(7, x.getPhone());

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

    public void updateUserAccount(UserAccount x) {
        xSql = "update UserAccounts set Password = ?, Role = ?, Email = ?, Phone = ? where UserID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getPassword());
            ps.setString(2, x.getRole());
            ps.setString(3, x.getEmail());
            ps.setString(4, x.getPhone());

            ps.setInt(5, x.getUserID());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
