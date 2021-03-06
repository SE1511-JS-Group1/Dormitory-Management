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

import dao.impl.AccountDAO;
import dao.impl.BoarderDAO;
import dao.impl.DomManagerDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
public class CheckAccountServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        String txt = request.getParameter("txt");
        BoarderDAO boarderDAO = new BoarderDAO();
        DomManagerDAO domManagerDAO = new DomManagerDAO();
        AccountDAO accountDAO = new AccountDAO();
        System.out.println(type);
        System.out.println(type + ":" + txt);
        try {
            if ((type.contains("user") && accountDAO.getOne(txt) != null)
                    || (type.contains("email") && domManagerDAO.checkEmailDomManager(txt))
                    || (type.contains("phone") && domManagerDAO.checkPhoneDomManager(txt))
                    || (type.contains("email") && boarderDAO.checkEmailBoarder(txt))
                    || (type.contains("phone") && boarderDAO.checkPhoneBoarder(txt))) {
                System.out.println("zoo");
                response.getWriter().print((type.contains("user") ? "T??i kho???n" : type.contains("phone") ? "S??? ??i???n tho???i" : "Email") + " ???? ???????c ????ng k??");
            } else if (type.contains("user")) {
                if (txt.length() < 5 || txt.length() > 19) {
                    response.getWriter().print("T??n ????ng nh???p ph???i trong kho???ng t??? 5-19 k?? t???");
                } else {
                    response.getWriter().print("");
                }
            } else if (type.contains("phone")) {
                if (!txt.matches("\\d+") || txt.length() != 10) {
                    response.getWriter().print("S??? ??i???n tho???i ch??? n??n g???m 10 ch??? s???!");
                } else {
                    response.getWriter().print("");
                }
            } else {
                response.getWriter().print("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println("hello post");
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
