/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.AccountDAO;
import dao.DomDAO;
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
        request.setAttribute("message_login", request.getParameter("message") == null ? "" : request.getParameter("message"));
        request.setAttribute("username", request.getParameter("username") == null ? "" : request.getParameter("username"));
        request.setAttribute("password", request.getParameter("password") == null ? "" : request.getParameter("password"));
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
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
                request.getRequestDispatcher("home/username=" + username + "&password=" + password).forward(request, response);
            } else {
                request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        AccountDAO accountDAO = new AccountDAO();
        Account account = (Account) accountDAO.getOne(username);
        if (account != null && account.getPassWord().equals(password)) {
            request.getSession().setAttribute("account", account);
            switch (account.getRole()) {
                case 1:
                    request.getRequestDispatcher("/admin/home").forward(request, response);
                    break;
                case 2:
                    request.getRequestDispatcher("/staff/home_staff.jsp").forward(request, response);
                    break;
                case 3:
                    request.getRequestDispatcher("/boarder/home_boarder.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        } else {
            request.setAttribute("message_login", "Sai tên đăng nhập hoặc mật khẩu!");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
