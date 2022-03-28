/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.boarder;

import dao.impl.BoarderDAO;
import dao.impl.TransactionDAO;
import dao.impl.WalletDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Boarder;
import model.Transaction;
import model.Wallet;

/**
 *
 * @author Dell
 */
@WebServlet(name = "BoarderWalletServlet", urlPatterns = {"/boarder/wallet"})
public class BoarderWalletServlet extends HttpServlet {


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
            request.setAttribute("page", "wallet");
            request.setAttribute("act", "view");
            Account account = (Account) request.getSession().getAttribute("account");
            String user = account.getUserName();
            BoarderDAO boarderDAO = new BoarderDAO();
            Boarder boarder = (Boarder) boarderDAO.getOne(user);
            WalletDAO walletDAO = new WalletDAO();
            Wallet wallet = (Wallet)walletDAO.getOne(user);
            TransactionDAO tdao = new TransactionDAO();
            ArrayList<Transaction> transactions = tdao.getTransactions(wallet.getWalletId(), 1);
            request.setAttribute("curpage", 1);
            request.setAttribute("numberofpage", tdao.getNumberPage(wallet.getWalletId()));
            request.setAttribute("Transactions", transactions);
            request.setAttribute("balance", wallet.getBalance());
            request.setAttribute("boardername", boarder.getBoarderName());
            request.getRequestDispatcher("boarderwallet.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BoarderWalletServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            int page = Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
            request.setAttribute("page", "wallet");
            request.setAttribute("act", "view");
            Account account = (Account) request.getSession().getAttribute("account");
            String user = account.getUserName();
            BoarderDAO boarderDAO = new BoarderDAO();
            Boarder boarder = (Boarder) boarderDAO.getOne(user);
            WalletDAO walletDAO = new WalletDAO();
            Wallet wallet = (Wallet)walletDAO.getOne(user);
            TransactionDAO tdao = new TransactionDAO();
            ArrayList<Transaction> transactions = tdao.getTransactions(wallet.getWalletId(), page);
            request.setAttribute("curpage", page);
            request.setAttribute("numberofpage", tdao.getNumberPage(wallet.getWalletId()));
            request.setAttribute("Transactions", transactions);
            request.setAttribute("balance", wallet.getBalance());
            request.setAttribute("boardername", boarder.getBoarderName());
            request.getRequestDispatcher("boarderwallet.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BoarderWalletServlet.class.getName()).log(Level.SEVERE, null, ex);
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
