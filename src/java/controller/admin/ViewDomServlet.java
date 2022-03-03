/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Controller Admin
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-23      2.0                 DucHT           Update code
 */
package controller.admin;

import dao.impl.DomDAO;
import dao.impl.RoomCategoryDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DomInformation;

/**
 *
 * @author Dell
 */
public class ViewDomServlet extends HttpServlet {


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
            request.setAttribute("page", "dom");
            DomDAO domDAO = new DomDAO();
            RoomCategoryDAO roomCategoryDAO = new RoomCategoryDAO();
            ArrayList<Object> categorys = roomCategoryDAO.getAll();
            ArrayList<DomInformation> domInformations = domDAO.getDomInformations();
            request.setAttribute("domDAO", domDAO);
            request.setAttribute("categorys", categorys);
            request.setAttribute("domInformations", domInformations);
            request.getRequestDispatcher("dom_view_admin.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ViewDomServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            DomDAO domDAO = new DomDAO();
            domDAO.addNewDom();
        } catch (SQLException ex) {
            Logger.getLogger(ViewDomServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        doGet(request, response);
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
