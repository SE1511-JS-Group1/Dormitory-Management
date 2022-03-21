/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.boarder;

import controller.admin.ViewRoomServlet;
import dao.impl.BoarderDAO;
import dao.impl.BoardingInformationDAO;
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
import model.BoardingInformation;

/**
 *
 * @author windc
 */
public class LoadRenewServlet extends HttpServlet {

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
        request.setAttribute("page", "room");
        request.setAttribute("act", "renew");
        try {
            Account act = (Account) request.getSession().getAttribute("account");
            BoarderDAO boarderDAO = new BoarderDAO();
            Boarder boarder = (Boarder) boarderDAO.getOne(act.getUserName());
            BoardingInformationDAO boardingInformationDAO = new BoardingInformationDAO();
            BoardingInformation boardingInformation = boardingInformationDAO.getBoardingInformation(boarder.getBoarderID());
            if (boardingInformation != null) {
                request.setAttribute("infor", boardingInformation);
                Cookie[] cookies = request.getCookies();
                for (Cookie c : cookies) {
                    System.out.println(c.getName());
                    if (c.getName().equals("Renew" + boarder.getBoarderID())) {
                        String[] reqString = c.getValue().split("\\D");
                        int month = Integer.parseInt(reqString[1]);
                        request.setAttribute("month", month);
                    }
                }
                request.getRequestDispatcher("renew_boarder.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewRoomServlet.class.getName()).log(Level.SEVERE, null, ex);
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
