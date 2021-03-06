/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Controller Boarder
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-03-10      2.0                 AnhNNV           Update code
 */
package controller.boarder;

import dao.impl.BoarderDAO;
import dao.impl.BoardingInformationDAO;
import dao.impl.RoomDAO;
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
import model.Room;

/**
 *
 * @author Admin
 */
public class BookingServlet extends HttpServlet {

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
        try {
            int roomID = Integer.parseInt(request.getParameter("roomId"));
            int bedNo = Integer.parseInt(request.getParameter("bedno"));
            Account act = (Account) request.getSession().getAttribute("account");
            BoarderDAO boarderDAO = new BoarderDAO();
            Boarder boarder = (Boarder) boarderDAO.getOne(act.getUserName());
            Cookie Booking = new Cookie("Book" + boarder.getBoarderID(), boarder.getBoarderID() + "|" + roomID + "|" + bedNo);
            Booking.setPath(request.getContextPath());
            Booking.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(Booking);
            RoomDAO roomDAO = new RoomDAO();
            BoardingInformationDAO boardingInformationDAO = new BoardingInformationDAO();
            Room room = (Room) roomDAO.getOne(roomID);
            BoardingInformation boardingInformation = new BoardingInformation(room, boarder, bedNo, null, null);
            request.setAttribute("infor", boardingInformation);
        } catch (SQLException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("waiting_book_boarder.jsp").forward(request, response);
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
