/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangndx.servlets;

import hoangndx.errors.UpdateErrorDTO;
import hoangndx.login.LoginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

    private final String ERROR_PAGE = "search.jsp";

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

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String searhValue = request.getParameter("txtSearchValue");
        String urlRewriting = ERROR_PAGE;
        try {
            UpdateErrorDTO errorDTO = new UpdateErrorDTO();
            boolean err = false;
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                errorDTO.setErrorpassword("pasword length requires 6 - 20 chars");
                err = true;
            }
            if (password.trim().length() == 0) {
                errorDTO.setErrorpassword("Please enter your password");
                err = true;
            }
            if (err) {
                HttpSession session = request.getSession();
                session.setAttribute("UPDATEERROR", errorDTO);
                urlRewriting = "DispatchServlet"
                        + "?btAction=Search"
                        + "&txtSearch=" + searhValue;

            } else {
                LoginDAO dao = new LoginDAO();
                boolean result = dao.updateAccount(username, password);
                if (result) {
                    urlRewriting = "DispatchServlet"
                            + "?btAction=Search"
                            + "&txtSearch=" + searhValue;
                }
            }
        } catch (NamingException e) {
            log("UpdateServlet Naming : " + e.getMessage());
        } catch (SQLException e) {
            log("UpdateServlet SQL : " + e.getMessage());
        } finally {
            response.sendRedirect(urlRewriting);
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
