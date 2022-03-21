/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.boarder;

import controller.admin.ViewRoomServlet;
import dao.impl.BoarderDAO;
import dao.impl.BoardingInformationDAO;
import dao.impl.DomDAO;
import dao.impl.RoomDAO;
import dao.impl.RoomStatusDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Boarder;
import model.BoardingInformation;
import model.Dom;
import model.RoomStatus;

/**
 *
 * @author windc
 */
@WebServlet(name = "LoadEditBookingServlet", urlPatterns = {"/boarder/load"})
public class LoadEditBookingServlet extends HttpServlet {

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
        request.setAttribute("act", "change");
        try {

            Account act = (Account) request.getSession().getAttribute("account");
            RoomStatusDAO roomStatusDAO = new RoomStatusDAO();
            DomDAO domDAO = new DomDAO();
            BoarderDAO boarderDAO = new BoarderDAO();
            Boarder boarder = (Boarder) boarderDAO.getOne(act.getUserName());
            Cookie[] cookies = request.getCookies();
            RoomDAO roomDAO = new RoomDAO();
            BoardingInformationDAO boardingInformationDAO = new BoardingInformationDAO();
            BoardingInformation boardingInformation = boardingInformationDAO.getBoardingInformation(boarder.getBoarderID());
            String bookcookie = "";
            for (Cookie c : cookies) {
                if (c.getName().equals("Book" + boarder.getBoarderID())) {
                    c.setPath(request.getContextPath());
                    bookcookie = c.getValue();
                    break;
                }
            }
            if (bookcookie.isEmpty() && boardingInformation == null) {
                request.getRequestDispatcher("ErrorPageBoarder.jsp").forward(request, response);
            } else {
                request.setAttribute("doms", domDAO.getAll());
                String domID = request.getParameter("dom") == null ? "A" : request.getParameter("dom");
                Object dom = domDAO.getOne(domID);
                ArrayList<RoomStatus> map = roomStatusDAO.getDomStatus((Dom) dom);
                request.setAttribute("roomStatusDAO", roomStatusDAO);
                request.setAttribute("dom", dom);
                request.setAttribute("mapdom", map);
                request.getRequestDispatcher("change_boarder_room.jsp").forward(request, response);
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
