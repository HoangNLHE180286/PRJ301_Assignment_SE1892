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
public class OrderDetailDAO extends MyDAO {

    public List<OrderDetail> getOrderDetailList(String orderID) {
        List<OrderDetail> l = new ArrayList<OrderDetail>();
        xSql = "select * from OrderDetails where OrderID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, orderID);
            rs = ps.executeQuery();

            Order xOrderID;
            Product xProductID;
            int xQuantity;
            double xUnitPrice;
            double xDiscount;
            OrderDetail x;

            while (rs.next()) {
                OrderDAO od = new OrderDAO();
                xOrderID = od.getOrderByID(rs.getString("OrderID"));
                ProductDAO pd = new ProductDAO();
                xProductID = pd.getProductByID(rs.getInt("ProductID"));
                xQuantity = rs.getInt("Quantity");
                xUnitPrice = rs.getDouble("UnitPrice");
                xDiscount = rs.getDouble("Discount");

                x = new OrderDetail(xOrderID, xProductID, xQuantity, xUnitPrice, xDiscount);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (l);
    }
    
    public void insertOrderDetail(OrderDetail x) {
        xSql = "insert into OrderDetails (OrderID, ProductID, Quantity, UnitPrice, Discount) values (?, ?, ?, ?, ?)";
        
        String orderID = x.getOrderID().getOrderID();
        int productID = x.getProductID().getProductID();

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, orderID);
            ps.setInt(2, productID);
            ps.setInt(3, x.getQuantity());
            ps.setDouble(4, x.getUnitPrice());
            ps.setDouble(5, x.getDiscount());

            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteOrderDetail(String OrderID) {
        xSql = "delete from OrderDetails where OrderID = ?";
        
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

//    public void updateOrderDetail(String OrderID, OrderDetail x) {
//        xSql = "update OrderDetails set ProductID = ?, Quantity = ?, UnitPrice = ?, Discount = ? where OrderID like ?";
//        
//        String orderID = x.getOrderID().getOrderID();
//        int productID = x.getProductID().getProductID();
//        
//        try {
//            ps = con.prepareStatement(xSql);
//            ps.setInt(1, productID);
//            ps.setInt(2, x.getQuantity());
//            ps.setDouble(3, x.getUnitPrice());
//            ps.setDouble(4, x.getDiscount());
//            
//            ps.setString(5, orderID);
//            ps.executeUpdate();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
