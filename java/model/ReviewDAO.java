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
public class ReviewDAO extends MyDAO {

    public List<Review> getReviewList(int productID) {
        List<Review> l = new ArrayList<Review>();
        xSql = "select * from Reviews where ProductID = " + productID;
        
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            UserAccount xUserID;
            Product xProductID;
            float xRating;
            Date xReviewDate;
            String xComment;
            Review x;

            while (rs.next()) {
                UserAccountDAO uad = new UserAccountDAO();
                xUserID = uad.getUserAccountByID(rs.getInt("UserID"));
                ProductDAO pd = new ProductDAO();
                xProductID = pd.getProductByID(rs.getInt("ProductID"));
                xRating = rs.getFloat("Rating");
                xComment = rs.getString("Comment");
                xReviewDate = rs.getDate("ReviewDate");

                x = new Review(xUserID, xProductID, xRating, xComment, xReviewDate);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (l);
    }

//    public Review getReviewByUserID(int userID) {
//        Product xProductID;
//        float xRating;
//        String xReview;
//        Review x = new Review();
//        xSql = "select * from Reviews where UserID = ?";
//        
//        try {
//            ps = con.prepareStatement(xSql);
//            ps.setInt(1, userID);
//            rs = ps.executeQuery();
//
//            UserAccountDAO uad = new UserAccountDAO();
//            UserAccount xUserID = uad.getUserAccountByID(rs.getInt("userID"));
//            int yUserID = xUserID.getUserID();
//            if (userID == yUserID) {
//                while (rs.next()) {
//                    ProductDAO pd = new ProductDAO();
//                    xProductID = pd.getProductByID(rs.getInt("productID"));
//                    xRating = rs.getFloat("rating");
//                    xReview = rs.getString("review");
//
//                    x = new Review(xUserID, xProductID, xRating, xReview);
//                }
//            } else {
//                return null;
//            }
//
//            rs.close();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return (x);
//    }
    
    public void insertReview(Review x) {
        xSql = "insert into Reviews (UserID, ProductID, Rating, ReviewDate) values (?, ?, ?, ?)";
        
        int userID = x.getUserID().getUserID();
        int productID = x.getProductID().getProductID();
        java.sql.Date reviewDate = (java.sql.Date) x.getReviewDate();
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
            ps.setInt(2, productID);
            ps.setFloat(3, x.getRating());
            ps.setDate(4, reviewDate);

            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
//    public void deleteReview(int ProductID) {
//        xSql = "delete from Reviews where ProductID = ?";
//        
//        try {
//            ps = con.prepareStatement(xSql);
//            ps.setInt(1, ProductID);
//    
//            ps.executeUpdate();
//            //con.commit();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void updateReview(int ProductID, Review x) {
        xSql = "update Reviews set UserID = ?, Rating = ?, ReviewDate = CURRENT_TIMESTAMP where ProductID = ?";
        
        int userID = x.getUserID().getUserID();
        int productID = x.getProductID().getProductID();
        
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
            ps.setFloat(2, x.getRating());
            
            ps.setInt(3, productID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public float avgRating(int productID){
        List<Review> l = this.getReviewList(productID);
        float avg = 0;
        for (Review rev : l) {
            avg += rev.getRating();
        }
        avg = avg / l.size();
        return (float) (Math.floor(avg * 10) / 10);
    }

}
