/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangndx.servlets;

import hoangndx.errors.LoginErrorDTO;
import hoangndx.login.LoginDAO;
import hoangndx.login.LoginDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {
    private final String INVALID_PAGE = "invalid.html";
    private final String ADMIN_PAGE = "AdminPage.jsp";
    private final String SEARCH_PAGE = "search.jsp";
   private final String LOGIN_ERROR_PAGE="login.jsp";
    

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
        //parameter dang o trong request object
        String url = INVALID_PAGE;
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        //System.out.println("username"+ username);
        
        
        try {
            LoginErrorDTO errDTO = new LoginErrorDTO();
            boolean error = false;
            if(username.trim().length()<6 || username.trim().length()>20){
                error = true;
                errDTO.setErrorusername("username length requires 6 - 20 chars");
            }
            if(password.trim().length()<6 || password.trim().length()>20){
                error = true;
                errDTO.setErrorpassword("pasword length requires 6 - 20 chars");
            }
            if(username.trim().length()==0){
                error =  true;
                errDTO.setErrorusername("Please enter your username");
            }
            if(password.trim().length()==0){
                error = true;
                errDTO.setErrorpassword("Please enter your password");
            }
            if(error){
                request.setAttribute("ERROR", errDTO);
                url = LOGIN_ERROR_PAGE;
            }else{
               LoginDAO loginDAO = new LoginDAO();
                LoginDTO user  = loginDAO.checkLogin(username, password);
                
                
//                out.println("Test Connection is" + result);
                if(user != null) {
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                    
                    //get fullname
                    //set Atribute
                   //url = ADMIN_PAGE; 
                   url = ADMIN_PAGE;
                    Cookie cookie = new Cookie(username, password);
                    cookie.setMaxAge(60*1);
                    response.addCookie(cookie);
                    
                }//end if login is ok 
//                else{
//                    request.setAttribute("INVALID", "Error");
//                }
            }
            
        }catch(NamingException e){
            log("LoginServlet Naming : " + e.getMessage());
        }catch(SQLException e){
            log("LoginServlet SQL : " + e.getMessage());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
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
