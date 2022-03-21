/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dao.impl.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Feedback;

/**
 *
 * @author XuanDinh
 */
public class ViewFeedbackServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

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
        request.setAttribute("page", "Feedback");
        try (PrintWriter out = response.getWriter()) {
            FeedbackDAO feedback = new FeedbackDAO();
            ArrayList<Feedback> listFeedback = feedback.getpagefeedback(1);
            request.setAttribute("feedback", listFeedback);
            request.setAttribute("curpage", 1);
            request.setAttribute("numberofpage", feedback.getTotalPage() );
            request.getRequestDispatcher("feedback_view_admin.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ViewRoomServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        request.setAttribute("page", "Feedback");
        
        try {
            FeedbackDAO feedback = new FeedbackDAO();
            int curpage = Integer.parseInt(request.getParameter("page"));
            if (request.getParameter("date1").trim().isEmpty() || request.getParameter("date2").trim().isEmpty()) {
                ArrayList<Feedback> listFeedback = feedback.getpagefeedback(curpage);
                request.setAttribute("feedback", listFeedback);
                request.setAttribute("numberofpage", feedback.getTotalPage());
            } else {
                Date date1 = Date.valueOf(request.getParameter("date1"));
                Date date2 = Date.valueOf(request.getParameter("date2"));
                ArrayList<Feedback> listFeedback = feedback.getpagefeedback(curpage, date1, date2);
                request.setAttribute("feedback", listFeedback);
                request.setAttribute("date1", date1);
                request.setAttribute("date2", date2);
                request.setAttribute("numberofpage", feedback.getTotalPage(date1, date2));
            }
            request.setAttribute("curpage", Integer.parseInt(request.getParameter("page")));
            request.getRequestDispatcher("feedback_view_admin.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ViewRoomServlet.class.getName()).log(Level.SEVERE, null, ex);
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
