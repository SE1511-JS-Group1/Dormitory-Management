/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.boarder;

import controller.admin.ViewRoomServlet;
import dao.impl.AccountDAO;
import dao.impl.BoarderDAO;
import dao.impl.BoardingInformationDAO;
import dao.impl.DomDAO;
import dao.impl.RoomDAO;
import dao.impl.RoomStatusDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
import model.Dom;
import model.Room;
import model.RoomStatus;

/**
 *
 * @author windc
 */
public class DeleteBookingServlet extends HttpServlet {

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
        request.setAttribute("act", "book");
        try (PrintWriter out = response.getWriter()) {
            BoarderDAO boarderDAO = new BoarderDAO();
            int boarderID = Integer.parseInt(request.getParameter("boarderId"));
            System.out.println(boarderID);
            Cookie[] cookies = request.getCookies();
            for (Cookie c : cookies) {
                if (c.getName().equals("Book" + boarderID)) {
                    System.out.println("Xoa Cookie");
                    c.setPath(request.getContextPath());
                    c.setMaxAge(0);
                    response.addCookie(c);
                    break;
                }
            }
            try {
                RoomStatusDAO roomStatusDAO = new RoomStatusDAO();
                DomDAO domDAO = new DomDAO();
                request.setAttribute("doms", domDAO.getAll());
                String domID = request.getParameter("dom") == null ? "A" : request.getParameter("dom");
                Object dom = domDAO.getOne(domID);
                ArrayList<RoomStatus> map = roomStatusDAO.getDomStatus((Dom) dom);
                request.setAttribute("roomStatusDAO", roomStatusDAO);
                request.setAttribute("dom", dom);
                request.setAttribute("mapdom", map);
                request.getRequestDispatcher("boarder_room_book.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ViewRoomServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
