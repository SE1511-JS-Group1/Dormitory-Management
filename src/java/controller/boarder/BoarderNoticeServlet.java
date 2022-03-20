/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Boarder notices servlet
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-27      1.0                 HuyLQ           Update code
 */
package controller.boarder;

import dao.impl.AccountDAO;
import dao.impl.BoarderDAO;
import dao.impl.NoticeDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Boarder;
import model.Notice;

/**
 *
 * @author Admin
 */
public class BoarderNoticeServlet extends HttpServlet {

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
            AccountDAO accountDAO = new AccountDAO();
            BoarderDAO boarderDAO = new BoarderDAO();
            NoticeDAO noticeDAO = new NoticeDAO();

            Account account = (Account) request.getSession().getAttribute("account");
            String user = account.getUserName();
            Boarder boarder = boarderDAO.getBoarderByUserName(user);
            int id = boarder.getBoarderID();
            ArrayList<Notice> notices = noticeDAO.getNoticesByBoarderId(id, 1);
            request.setAttribute("ListNotice", notices);
            request.setAttribute("curpage", 1);
            request.setAttribute("numberofpage", noticeDAO.getTotalPage(id));
            request.setAttribute("page", "notice");
            request.getRequestDispatcher("notices_view_boarder.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BoarderNoticeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        int curpage = Integer.parseInt(request.getParameter("page"));
        try {
            AccountDAO accountDAO = new AccountDAO();
            BoarderDAO boarderDAO = new BoarderDAO();
            NoticeDAO noticeDAO = new NoticeDAO();

            Account account = (Account) request.getSession().getAttribute("account");
            String user = account.getUserName();
            Boarder boarder = boarderDAO.getBoarderByUserName(user);
            int id = boarder.getBoarderID();
            ArrayList<Notice> notices = noticeDAO.getNoticesByBoarderId(id, curpage);
            request.setAttribute("ListNotice", notices);
            request.setAttribute("curpage", curpage);
            request.setAttribute("numberofpage", noticeDAO.getTotalPage(id));
            request.setAttribute("page", "notice");
            request.getRequestDispatcher("notices_view_boarder.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BoarderNoticeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
