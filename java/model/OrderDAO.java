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
public class OrderDAO extends MyDAO {

    public List<Order> getOrderList(int userID) {
        List<Order> l = new ArrayList<Order>();
        xSql = "select * from Orders where UserID = " + userID;
        
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            String xOrderID;
            UserAccount xUserID;
            Date xOrderDate;
            Date xRequiredDate;
            double xFreight;
            Payment xPaymentID;
            Order x;
            UserAccountDAO uad = new UserAccountDAO();
            while (rs.next()) {
                xOrderID = rs.getString("OrderID");
                xUserID = uad.getUserAccountByID(userID);
                xOrderDate = rs.getDate("OrderDate");
                xRequiredDate = rs.getDate("RequiredDate");
                xFreight = rs.getDouble("Freight");                

                x = new Order(xOrderID, xUserID , xOrderDate, xRequiredDate, xFreight);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (l);
    }
    
     public Order getOrderByID(String orderID) {
        Date xOrderDate;
        UserAccount xUserID;
        Date xRequiredDate;
        double xFreight;
        Payment xPaymentID;
        Order x = new Order();
        UserAccountDAO uad = new UserAccountDAO();
        xSql = "select * from Orders where OrderID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, orderID);
            rs = ps.executeQuery();
            while (rs.next()) {
                xOrderDate = rs.getDate("OrderDate");
                xUserID = uad.getUserAccountByID(rs.getInt("UserID"));
                xRequiredDate = rs.getDate("RequiredDate");
                xFreight = rs.getDouble("Freight");

                x = new Order(orderID, xUserID, xOrderDate, xRequiredDate, xFreight);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }
    
    public void insertOrder(Order x) {
        xSql = "insert into Orders (OrderID, UserID, OrderDate, Freight, RequiredDate) values (?, ?, ?, ?, ?)";
        
        java.sql.Date od = (java.sql.Date) x.getOrderDate();
        java.sql.Date rd = (java.sql.Date) x.getRequiredDate();
        int userID = x.getUserID().getUserID();
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getOrderID());
            ps.setInt(2, userID);
            ps.setDate(3, od);
            ps.setDouble(4, x.getFreight());
            ps.setDate(5, rd);

            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int userID, String OrderID) {
        xSql = "delete from Orders where (OrderID = ?) and (userID = ?)" ;
        
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, OrderID);
            ps.setInt(2, userID);
            ps.executeUpdate();
            //con.commit();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void updateOrder(String OrderID, int userID, Order x) {
//        xSql = "update Orders set OrderDate = ?, Freight = ?, RequiredDate = ? where (OrderID = ?) and (UserID = ?)";
//        
//        java.sql.Date od = (java.sql.Date) x.getOrderDate();
//        java.sql.Date rd = (java.sql.Date) x.getRequiredDate();
//
//        try {
//            ps = con.prepareStatement(xSql);
//            ps.setDate(1, od);
//            ps.setDouble(2, x.getFreight());
//            ps.setDate(3, rd);
//            
//            ps.setString(5, x.getOrderID());
//            ps.setInt(6, userID);
//            ps.executeUpdate();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
