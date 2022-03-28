/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Controller Boarder
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-02-23      2.0                 DinhLX           Update code
 */
package controller.boarder;

import dao.impl.AccountDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author XuanDinh
 */
public class BoarderChangePasswordServlet extends HttpServlet {


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
        request.getRequestDispatcher("boarder_change_password.jsp").forward(request, response);
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
        try {
            String username = ((Account) request.getSession().getAttribute("account")).getUserName();
            String newpassword = request.getParameter("newpass");
            AccountDAO accountDAO = new AccountDAO();
            accountDAO.update(newpassword, username);
            request.getRequestDispatcher("boarder_change_password.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BoarderChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("boarder_change_password.jsp").forward(request, response);
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
