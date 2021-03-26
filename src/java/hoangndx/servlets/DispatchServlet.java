/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangndx.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private final String DETELE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String UPDATE_CONTROLLER = "UpdateServlet";
    private final String COOKIE_CONTROLLER = "CookieServlet";
    private final String ADD_BOOK_TO_CART_CONTROLLER = "AddBookToCartServlet";
    private final String VIEW_CART_PAGE="viewCart.jsp";
    private final String REMOVE_BOOK_FROM_CART_CONTROLLER = "RemoveBookServlet";
    private final String PRODUCT_CONTROLLER = "ProductServlet";
    private final String LOGOUT_CONTROLLER="LogoutServlet";
    private final String CHECKOUT_CONTROLLER="CheckoutServlet";
    private final String SEARCH_PAGE = "search.jsp";

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
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE;
        String button = request.getParameter("btAction");
        System.out.println(request.getParameter("btAction"));
        try { 
           
           if (button == null) {
                url = COOKIE_CONTROLLER;
            } else if (button.equals("login")) {                               
                url = LOGIN_CONTROLLER;               
            } else if (button.equals("Search")) {
                url = SEARCH_LASTNAME_CONTROLLER;
            } else if(button.equals("delete")) {
                url = DETELE_ACCOUNT_CONTROLLER;
            } else if(button.equals("Update")) {
                url = UPDATE_CONTROLLER;
            } else if (button.equals("Add To Cart")){  
                url = ADD_BOOK_TO_CART_CONTROLLER;
            }  else if (button.equals("View Cart")){
                url = VIEW_CART_PAGE;
            }else if (button.equals("Remove")) {
                url = REMOVE_BOOK_FROM_CART_CONTROLLER;
            }else if (button.equals("Click here to buy book")){
                url = PRODUCT_CONTROLLER;
            }else if(button.equals("Log Out")) {
                url = LOGOUT_CONTROLLER;
            }else if(button.equals("Check out")){
                url = CHECKOUT_CONTROLLER;
            }else if(button.equals("Manage Account")){
                url = SEARCH_PAGE;
            }else if(button.equals("Login")){
                url = LOGIN_CONTROLLER;
            }
        } finally {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
                out.close();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
