/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Controller Admin
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-03-01      1.0                 DucHT           First Implement
 */
package controller.admin;

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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dom;
import model.RoomStatus;

/**
 *
 * @author Dell
 */
@WebServlet(name = "DisableRoomServlet", urlPatterns = {"/admin/disableroom"})
public class DisableRoomServlet extends HttpServlet {

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
        try {
            int roomID = Integer.parseInt(request.getParameter("room"));
            RoomDAO roomDAO = new RoomDAO();
            roomDAO.disableRoom(roomID);
            RoomStatusDAO roomStatusDAO = new RoomStatusDAO();
            DomDAO domDAO = new DomDAO();
            request.setAttribute("doms", domDAO.getAll());
            String domID = request.getParameter("dom") == null ? "A" : request.getParameter("dom");
            Object dom = domDAO.getOne(domID);
            ArrayList<RoomStatus> map = roomStatusDAO.getDomStatus((Dom) dom);
            request.setAttribute("dom", dom);
            request.setAttribute("mapdom", map);
            request.setAttribute("page", "room");
            request.getRequestDispatcher("room_view_admin.jsp").forward(request, response);
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
