/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.boarder;

import dao.impl.BoarderDAO;
import dao.impl.BoardingInformationDAO;
import dao.impl.ElectricAndWaterBillDAO;
import dao.impl.RoomFeeBillDAO;
import dao.impl.WalletDAO;
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
import model.Account;
import model.Boarder;
import model.BoardingInformation;
import model.ElectricAndWaterBill;
import model.RoomFeeBill;
import model.Wallet;

/**
 *
 * @author Dell
 */
@WebServlet(name = "FeeServlet", urlPatterns = {"/boarder/fee"})
public class FeeServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("page", "wallet");
            request.setAttribute("act", "pay");
            Account account = (Account) request.getSession().getAttribute("account");
            String user = account.getUserName();
            BoarderDAO boarderDAO = new BoarderDAO();
            Boarder boarder = (Boarder) boarderDAO.getOne(user);
            RoomFeeBillDAO rfbdao = new RoomFeeBillDAO();
            ArrayList<RoomFeeBill> rfb = rfbdao.getBills(boarder);
            BoardingInformationDAO bidao = new BoardingInformationDAO();
            BoardingInformation bi = bidao.getBoardingInformation(boarder.getBoarderID());
            request.setAttribute("boarding", bi);
            request.setAttribute("RoomFees", rfb);
            WalletDAO walletDAO = new WalletDAO();
            Wallet wallet = (Wallet)walletDAO.getOne(user);
            request.setAttribute("balance", wallet.getBalance());
            ElectricAndWaterBillDAO eawbdao = new ElectricAndWaterBillDAO();
            ArrayList<ElectricAndWaterBill> eawbs = eawbdao.getBoarderBills(bi.getRoom());
            request.setAttribute("ElectricWater", eawbs);
            request.getRequestDispatcher("boarderfee.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(FeeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
