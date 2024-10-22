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

//    public List<Payment> getPaymentList(String orderID) {
//        List<Payment> l = new ArrayList<Payment>();
//        xSql = "select * from Payments where Or";
//        
//        try {
//            ps = con.prepareStatement(xSql);
//            rs = ps.executeQuery();
//
//            String xPaymentID;
//            String xPaymentMethod;
//            Date xPaymentDate;
//            double xPrice;
//            Payment x;
//
//            while (rs.next()) {
//                xPaymentID = rs.getString("PaymentID");
//                UserAccountDAO uad = new UserAccountDAO();
//                xPaymentMethod = rs.getString("PaymentMethod");
//                xPaymentDate = rs.getDate("PaymentDate");
//                xPrice = rs.getDouble("Price");
//
//                x = new Payment(xPaymentID, xPaymentMethod, xPaymentDate, xPrice);
//                l.add(x);
//            }
//            rs.close();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return (l);
//    }

    public Payment getPaymentByID(String orderID) {

        UserAccount xUserID;
        String xPaymentMethod;
        Date xPaymentDate;
        double xPrice;        
        Payment x = new Payment();
        xSql = "select * from Payments where OrderID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, orderID);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                UserAccountDAO uad = new UserAccountDAO();
                xPaymentMethod = rs.getString("PaymentMethod");
                xPaymentDate = rs.getDate("PaymentDate");
                xPrice = rs.getDouble("Price");

                x = new Payment(xPaymentMethod, xPaymentMethod, xPaymentDate, xPrice);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }
    
    public void insertPayment(Payment x) {
        xSql = "insert into Payments (OrderID, PaymentMethod, PaymentDate, Price) values (?, ?, ?, ?)";
        
        java.sql.Date pd = (java.sql.Date) x.getPaymentDate();

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getOrderID());
            ps.setString(2, x.getPaymentMethod());
            ps.setDate(3, pd);
            ps.setDouble(4, x.getPrice());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void deletePayment(String PaymentID) {
//        xSql = "delete from Payments where PaymentID like ?";
//        
//        try {
//            ps = con.prepareStatement(xSql);
//            ps.setString(1, PaymentID);
//            
//            ps.executeUpdate();
//            //con.commit();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
	
//    public void updatePayment(String PaymentID, Payment x) {
//        xSql = "update Payments set PaymentMethod = ?, PaymentDate = ?, Price = ? where PaymentID like ?";
//        
//        java.sql.Date pd = (java.sql.Date) x.getPaymentDate();
//        
//        try {
//            ps = con.prepareStatement(xSql);
//            ps.setString(1, x.getPaymentMethod());
//            ps.setDate(2, pd);
//            ps.setDouble(3, x.getPrice());            
//            
//            ps.setString(4, x.getPaymentID());
//            ps.executeUpdate();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
