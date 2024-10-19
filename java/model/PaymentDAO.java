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
public class PaymentDAO extends MyDAO {

    public List<Payment> getPaymentList() {
        List<Payment> l = new ArrayList<Payment>();
        xSql = "select * from Payments";
        
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            String xPaymentID;
            UserAccount xUserID;
            String xPaymentMethod;
            Date xPaymentDate;
            double xPrice;
            Payment x;

            while (rs.next()) {
                xPaymentID = rs.getString("paymentID");
                UserAccountDAO uad = new UserAccountDAO();
                xUserID = uad.getUserAccountByID(rs.getInt("userID"));
                xPaymentMethod = rs.getString("paymentMethod");
                xPaymentDate = rs.getDate("paymentDate");
                xPrice = rs.getDouble("price");

                x = new Payment(xPaymentID, xUserID, xPaymentMethod, xPaymentDate, xPrice);
                l.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (l);
    }

    public Payment getPaymentByID(String paymentID) {
        if (paymentID == null || paymentID.trim().equals("")) {
            return null;
        }

        UserAccount xUserID;
        String xPaymentMethod;
        Date xPaymentDate;
        double xPrice;        
        Payment x = new Payment();
        xSql = "select * from Payments where PaymentID like ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, paymentID);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                UserAccountDAO uad = new UserAccountDAO();
                xUserID = uad.getUserAccountByID(rs.getInt("UserID"));
                xPaymentMethod = rs.getString("paymentMethod");
                xPaymentDate = rs.getDate("paymentDate");
                xPrice = rs.getDouble("price");

                x = new Payment(xPaymentMethod, xUserID, xPaymentMethod, xPaymentDate, xPrice);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }
    
    public void insertPayment(Payment x) {
        xSql = "insert into Payments (PaymentID, UserID, PaymentMethod, PaymentDate, Price) values (?, ?, ?, ?, ?)";
        
        int userID = x.getUserID().getUserID();
        java.sql.Date pd = (java.sql.Date) x.getPaymentDate();

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getPaymentID());
            ps.setInt(2, userID);
            ps.setString(3, x.getPaymentMethod());
            ps.setDate(4, pd);
            ps.setDouble(5, x.getPrice());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePayment(String PaymentID) {
        xSql = "delete from Payments where PaymentID like ?";
        
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, PaymentID);
            
            ps.executeUpdate();
            //con.commit();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePayment(String PaymentID, Payment x) {
        xSql = "update Payments set UserID = ?, PaymentMethod = ?, PaymentDate = ?, Price = ? where PaymentID like ?";
        
        int userID = x.getUserID().getUserID();
        java.sql.Date pd = (java.sql.Date) x.getPaymentDate();
        
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
            ps.setString(2, x.getPaymentMethod());
            ps.setDate(3, pd);
            ps.setDouble(4, x.getPrice());            
            
            ps.setString(5, x.getPaymentID());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
