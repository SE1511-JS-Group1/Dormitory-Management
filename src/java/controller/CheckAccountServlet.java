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
        if ((type.contains("user") && accountDAO.getOne(txt) != null)
                || (type.contains("email") && domManagerDAO.checkEmailDomManager(txt))
                || (type.contains("phone") && domManagerDAO.checkPhoneDomManager(txt))
                || (type.contains("email") && boarderDAO.checkEmailBoarder(txt))
                || (type.contains("phone") && boarderDAO.checkPhoneBoarder(txt))) {
            System.out.println("zoo");
            response.getWriter().print((type.contains("user") ? "Tài khoản" : type.contains("phone") ? "Số điện thoại" : "Email") + " đã được đăng ký");
        } else if (type.contains("user")) {
            if (txt.length() < 5 || txt.length() > 19) {
                response.getWriter().print("Tên đăng nhập phải trong khoảng từ 5-19 ký tự");
            } else {
                response.getWriter().print("");
            }
        } else if (type.contains("phone")) {
            if (!txt.matches("\\d+") || txt.length() != 10) {
                response.getWriter().print("Số điện thoại chỉ nên gồm 10 chữ số!");
            } else {
                response.getWriter().print("");
            }
        } else {
            response.getWriter().print("");
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
