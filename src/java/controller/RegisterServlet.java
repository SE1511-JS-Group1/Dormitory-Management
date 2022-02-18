/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dao.BoarderDAO;
import dao.DomManagerDAO;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Boarder;
import model.DomManager;
import model.Jobs;
import model.ManagerRegency;

/**
 *
 * @author lenovo_thinkpad
 */
public class RegisterServlet extends HttpServlet {

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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String fullName = request.getParameter("fullname");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        AccountDAO accountDAO = new AccountDAO();
        BoarderDAO boarderDAO = new BoarderDAO();
        DomManagerDAO domManagerDAO = new DomManagerDAO();
        Account account;
        boolean gender = request.getParameter("gender").equalsIgnoreCase("Male");
        Date dateofbirth = Date.valueOf(request.getParameter("dateofbirth"));
        String position = request.getParameter("position");
        if (position.equalsIgnoreCase("teacher") || position.equalsIgnoreCase("student")) {
            System.out.println("boarder");
            Jobs job = Jobs.valueOf(position);
            account = new Account(userName, password, 3);
            Boarder boarder = new Boarder(0, fullName, dateofbirth, gender, email, phone, job, account);
            accountDAO.insert(account);
            boarderDAO.insert(boarder);
            System.out.println(fullName + "," + userName + "," + password + "," + email + "," + phone + "," + gender + "," + job + "," + dateofbirth);
            request.getRequestDispatcher("login?username=" + userName + "&&password=" + password).forward(request, response);
        } else {
            System.out.println("staff");
            ManagerRegency regency = ManagerRegency.valueOf(position);
            account = new Account(userName, password, 2);
            DomManager domManager = new DomManager(0, fullName, gender, dateofbirth, email, phone, regency, account);
            accountDAO.insert(account);
            domManagerDAO.insert(domManager);
            request.getRequestDispatcher("waiting.jsp").forward(request, response);
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
