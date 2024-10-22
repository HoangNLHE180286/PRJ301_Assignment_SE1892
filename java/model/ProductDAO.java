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
public class ProductDAO extends MyDAO {

    public List<Product> getProductList() {
        List<Product> l = new ArrayList<Product>();
        xSql = "select * from Products";
        
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            int xProductID;
            String xProductName;
            Category xCategoryID;
            int xUnitInStock;
            String xImages;
            Product x;

            while (rs.next()) {
                xProductID = rs.getInt("ProductID");
                xProductName = rs.getString("ProductName");
                CategoryDAO cd = new CategoryDAO();
                xCategoryID = cd.getCategoryByID(rs.getInt("CategoryID"));
                xUnitInStock = rs.getInt("UnitInStock");
                xImages = rs.getString("Images");

                x = new Product(xProductID, xProductName, xCategoryID, xUnitInStock, xImages);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (l);
    }

    public Product getProductByID(int productID) {
        String xProductName;
        Category xCategoryID;
        int xUnitInStock;
        String xImages;
        Product x = new Product();
        xSql = "select * from Products where ProductID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            while (rs.next()) {
                xProductName = rs.getString("ProductName");
                CategoryDAO cd = new CategoryDAO();
                xCategoryID = cd.getCategoryByID(rs.getInt("CategoryID"));
                xUnitInStock = rs.getInt("UnitInStock");
                 xImages = rs.getString("Images");
                

                x = new Product(productID, xProductName, xCategoryID, xUnitInStock, xImages);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }
    
    public void insertProduct(Product x) {
        xSql = "insert into Products (ProductID, ProductName, CategoryID, UnitsInStock) values (?, ?, ?, ?)";
        
        int categoryID = x.getCategoryID().getCategoryID();

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, x.getProductID());
            ps.setString(2, x.getProductName());
            ps.setInt(3, categoryID);
            ps.setInt(4, x.getUnitInStock());

            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int ProductID) {
        xSql = "delete from Products where ProductID = ?";
        
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, ProductID);
            ps.executeUpdate();
            //con.commit();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(int ProductID, Product x) {
        xSql = "update Products set ProductName = ?, CategoryID = ?, UnitsInStock = ? where ProductID = ?";
        
        int categoryID = x.getCategoryID().getCategoryID();
        
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getProductName());
            ps.setInt(2, categoryID);
            ps.setInt(3, x.getUnitInStock());
            
            ps.setInt(4, x.getProductID());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
