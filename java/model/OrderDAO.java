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

    public List<Order> getOrderList() {
        List<Order> l = new ArrayList<Order>();
        xSql = "select * from Orders";
        
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            String xOrderID;
            Date xOrderDate;
            Date xRequiredDate;
            double xFreight;
            Payment xPaymentID;
            Order x;

            while (rs.next()) {
                xOrderID = rs.getString("orderID");
                xOrderDate = rs.getDate("orderDate");
                xRequiredDate = rs.getDate("requiredDate");
                xFreight = rs.getDouble("freight");
                PaymentDAO pd = new PaymentDAO();
                xPaymentID = pd.getPaymentByID(rs.getString("paymentID"));

                x = new Order(xOrderID, xOrderDate, xRequiredDate, xFreight, xPaymentID);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (l);
    }

    public Order getOrderByID(String orderID) {
        if (orderID == null || orderID.trim().equals("")) {
            return null;
        }

        Date xOrderDate;
        Date xRequiredDate;
        double xFreight;
        Payment xPaymentID;
        Order x = new Order();
        xSql = "select * from Orders where OrderID like ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, orderID);
            rs = ps.executeQuery();
            while (rs.next()) {
                xOrderDate = rs.getDate("orderDate");
                xRequiredDate = rs.getDate("requiredDate");
                xFreight = rs.getDouble("freight");
                PaymentDAO pd = new PaymentDAO();
                xPaymentID = pd.getPaymentByID(rs.getString("paymentID"));

                x = new Order(orderID, xOrderDate, xRequiredDate, xFreight, xPaymentID);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }
    
    public void insertOrder(Order x) {
        xSql = "insert into Orders (OrderID, OrderDate, Freight, RequiredDate, PaymentID) values (?, ?, ?, ?, ?)";
        
        java.sql.Date od = (java.sql.Date) x.getOrderDate();
        java.sql.Date rd = (java.sql.Date) x.getRequiredDate();
        String paymentID = x.getPaymentID().getPaymentID();

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getOrderID());
            ps.setDate(2, od);
            ps.setDouble(3, x.getFreight());
            ps.setDate(4, rd);
            ps.setString(5, paymentID);

            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(String OrderID) {
        xSql = "delete from Orders where OrderID like ?";
        
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, OrderID);
            ps.executeUpdate();
            //con.commit();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateOrder(String OrderID, Order x) {
        xSql = "update Orders set OrderDate = ?, Freight = ?, RequiredDate = ?, PaymentID = ? where OrderID like ?";
        
        java.sql.Date od = (java.sql.Date) x.getOrderDate();
        java.sql.Date rd = (java.sql.Date) x.getRequiredDate();
        String paymentID = x.getPaymentID().getPaymentID();
        
        try {
            ps = con.prepareStatement(xSql);
            ps.setDate(1, od);
            ps.setDouble(2, x.getFreight());
            ps.setDate(3, rd);
            ps.setString(4, paymentID);           
            
            ps.setString(5, x.getOrderID());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
