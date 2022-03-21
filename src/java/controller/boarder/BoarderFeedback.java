/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Controller Boarder
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-03-01      2.0                 DinhLX           First Implement
 */
package controller.boarder;

import dao.impl.BoarderDAO;
import dao.impl.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Boarder;
import model.Feedback;

/**
 *
 * @author XuanDinh
 */
public class BoarderFeedback extends HttpServlet {


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
       request.getRequestDispatcher("boarder_feedback.jsp").forward(request, response);
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
            String title = request.getParameter("title");
            String massage = request.getParameter("massage");
            Date date= new Date(System.currentTimeMillis());
            Time time = new Time(System.currentTimeMillis());
            Account account = (Account) request.getSession().getAttribute("account");
            String user = account.getUserName();
            BoarderDAO boarderDAO = new BoarderDAO();
            Boarder boarder = (Boarder)boarderDAO.getOne(user);
            Feedback feedback= new Feedback(0,
                    date,
                    time,
                    title + ": " + massage,
                    boarder);
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            feedbackDAO.insert(feedback);
            request.getRequestDispatcher("boarder_feedback.jsp").forward(request, response);
           
        } catch (SQLException ex) {
            Logger.getLogger(BoarderFeedback.class.getName()).log(Level.SEVERE, null, ex);
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
