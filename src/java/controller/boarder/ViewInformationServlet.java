/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Controller Boarder
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-03-08      2.0                 DucHT           Update code post
 */
package controller.boarder;

import dao.impl.BoarderDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Boarder;
import org.apache.axis.encoding.Base64;

/**
 *
 * @author Dell
 */
public class ViewInformationServlet extends HttpServlet {

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
        try {
            Account account = (Account) request.getSession().getAttribute("account");
            BoarderDAO boarderDAO = new BoarderDAO();
            Boarder boarder = (Boarder) boarderDAO.getOne(account.getUserName());
            request.setAttribute("boarder", boarder);
            request.getRequestDispatcher("boarder_information.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ViewInformationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        String id = request.getParameter("boarderid");
        String name = request.getParameter("name").trim();
        int gender = Integer.parseInt(request.getParameter("gender"));
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String job = request.getParameter("job");
        String infor = id + "|" + name + "|" + gender + "|" + dob + "|" + phone + "|" + email + "|" + job;
        // Encode infor
        byte[] bytes = infor.getBytes("UTF-8");
        infor = Base64.encode(bytes);
        Cookie editing = new Cookie("Edit" + id, infor);
        editing.setPath(request.getContextPath());
        editing.setMaxAge(60 * 60 * 24 * 30);
        for (Cookie c : request.getCookies()) {
            if (c.getName().equals(editing.getName())) {
                c.setPath(request.getContextPath());
                c.setMaxAge(0);
                response.addCookie(c);
                break;
            }
        }
        response.addCookie(editing);
        try {
            Account account = (Account) request.getSession().getAttribute("account");
            BoarderDAO boarderDAO = new BoarderDAO();
            Boarder boarder = (Boarder) boarderDAO.getOne(account.getUserName());
            request.setAttribute("boarder", boarder);
            request.getRequestDispatcher("boarder_information.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ViewInformationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
