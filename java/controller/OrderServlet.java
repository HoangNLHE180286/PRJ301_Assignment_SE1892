/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

/**
 *
 * @author Admin
 */
public class OrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response); // uncomment when needed
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response); // uncomment when needed
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

        String xQuantity = request.getParameter("quantity");
        String xProductID = request.getParameter("productID");
        int zQuantity;
        int zProductID;

        String err;
        try {
            zQuantity = Integer.parseInt(xQuantity);
            zProductID = Integer.parseInt(xProductID);
            
            ProductDAO pd = new ProductDAO();
            Product p = pd.getProductByID(zProductID);
            
            UserAccount ua = new UserAccount();
            
            if (zQuantity > p.unitInStock) {
                err = "Số lượng mua quá lớn, không thể đặt thêm!";
                request.setAttribute("#", err);
                request.getRequestDispatcher("#").forward(request, response);
                return;
            }   
            
            OrderDAO od = new OrderDAO();
            od.insertOrder(ua.getUserID(), zQuantity * 0.1);
            
            OrderDetailDAO odd = new OrderDetailDAO();
            if (zQuantity * p.unitPrice >= 500) {
                odd.insertOrderDetail(new OrderDetail(od.getOrderLastID(), p, zQuantity, 0.1));
            } else {
                odd.insertOrderDetail(new OrderDetail(od.getOrderLastID(), p, zQuantity, 0));
            }
            
            request.getRequestDispatcher("#").forward(request, response);
        } catch (NumberFormatException nfe) {
            err = "Số lượng không hợp lệ!";
            request.setAttribute("#", err);
            request.getRequestDispatcher("#").forward(request, response);
        }
        
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
