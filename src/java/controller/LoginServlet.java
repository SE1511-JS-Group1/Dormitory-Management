/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Controller Common
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-23      2.0                 DucHT           Update code
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.impl.AccountDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author lenovo_thinkpad
 */
public class LoginServlet extends HttpServlet {

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
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies.length);
        if (cookies.length > 2) {
            String username = "";
            String password = "";
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    username = c.getValue();
                }
            }
            for (Cookie c : cookies) {
                if (c.getName().equals("password")) {
                    password = c.getValue();
                }
            }
            if (!username.isEmpty() && !password.isEmpty()) {
                try {
                    AccountDAO accountDAO = new AccountDAO();
                    Account account;
                    account = (Account) accountDAO.getOne(username);
                    request.getSession().setAttribute("account", account);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (request.getSession().getAttribute("account") != null) {
            Account account = (Account) request.getSession().getAttribute("account");
            switch (account.getRole()) {
                case 1:
                    request.getRequestDispatcher("admin/home").forward(request, response);
                    break;
                case 2:
                    response.sendRedirect("staff/home");
                    break;
                case 3:
                    response.sendRedirect("boarder/home");
                    break;
            }
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
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
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            AccountDAO accountDAO = new AccountDAO();
            Account account = (Account) accountDAO.getOne(username);
            if (account != null && account.getPassWord().equals(password)) {
                request.getSession().setAttribute("account", account);
                request.getRequestDispatcher("/home").forward(request, response);
            } else {
                request.setAttribute("message_login", "Sai tên đăng nhập hoặc mật khẩu!");
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
