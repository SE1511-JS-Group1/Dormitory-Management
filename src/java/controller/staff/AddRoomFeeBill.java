/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.staff;

import dao.impl.BoarderDAO;
import dao.impl.BoardingInformationDAO;
import dao.impl.RoomCategoryDAO;
import dao.impl.RoomFeeBillDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Boarder;
import model.BoardingInformation;
import model.RoomFeeBill;

/**
 *
 * @author XuanDinh
 */
public class AddRoomFeeBill extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddRoomFeeBill</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddRoomFeeBill at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        try {
            BoardingInformationDAO infors = new BoardingInformationDAO();
            ArrayList<BoardingInformation> allBoarder = new ArrayList<>();
            for (BoardingInformation boardingInformation : allBoarder) {
                Boarder boarder = boardingInformation.getBoarder();
                Date date = new Date(System.currentTimeMillis());
                int month = (date.getMonth() + 1) % 12;
                String monthString;
                monthString = new DateFormatSymbols().getMonths()[month - 1];
                Date date1 = new Date((date.getMonth() == 11 ? date.getYear() + 1 : date.getYear()) - 1900,
                        (date.getMonth() + 1) % 12,
                        10);
                RoomFeeBill feebill = new RoomFeeBill(0, boarder, monthString, date1, false);
                RoomFeeBillDAO feebilldao = new RoomFeeBillDAO();
                feebilldao. insert(feebill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddRoomFeeBill.class.getName()).log(Level.SEVERE, null, ex);
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
