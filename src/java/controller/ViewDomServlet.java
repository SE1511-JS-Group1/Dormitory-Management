/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DomDAO;
import dao.RoomStatusDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Dom;
import model.Room;
import model.RoomStatus;

/**
 *
 * @author lenovo_thinkpad
 */
public class ViewDomServlet extends HttpServlet {

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
        RoomStatusDAO roomStatusDAO = new RoomStatusDAO();
        DomDAO domDAO = new DomDAO();
        request.getSession().setAttribute("doms", domDAO.getAll());
        String domID = request.getParameter("dom") == null ? "A" : request.getParameter("dom");
        Object dom = domDAO.getOne(domID);
        ArrayList<RoomStatus> map = roomStatusDAO.getDomStatus((Dom) dom);
        request.getSession().setAttribute("dom", dom);
        request.getSession().setAttribute("mapdom", map);
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            request.getRequestDispatcher("view_dom.jsp").forward(request, response);
        } else {
            switch (account.getRole()) {
                case 1:
                    request.getRequestDispatcher("/admin/view_dom_admin.jsp").forward(request, response);
                    break;
                case 2:
                    request.getRequestDispatcher("/staff/view_dom_staff.jsp").forward(request, response);
                    break;
                case 3:
                    request.getRequestDispatcher("/boarder/view_dom_boarder.jsp").forward(request, response);
                    break;
                default:
                    break;
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
