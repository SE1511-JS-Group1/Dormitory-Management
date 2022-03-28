/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.boarder;

import dao.impl.BoarderDAO;
import dao.impl.BoardingInformationDAO;
import dao.impl.DomManagerDAO;
import dao.impl.ElectricAndWaterBillDAO;
import dao.impl.NoticeDAO;
import dao.impl.RoomFeeBillDAO;
import dao.impl.WalletDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
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
import model.DomManager;
import model.ElectricAndWaterBill;
import model.Notice;
import model.RoomFeeBill;
import model.Wallet;

/**
 *
 * @author Dell
 */
@WebServlet(name = "PayingServlet", urlPatterns = {"/boarder/paying"})
public class PayingServlet extends HttpServlet {

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
        System.out.println("Zoooooo");
        try {
            int billid = Integer.parseInt(request.getParameter("billid"));
            int type = Integer.parseInt(request.getParameter("type"));
            Account account = (Account) request.getSession().getAttribute("account");
            String user = account.getUserName();
            BoarderDAO boarderDAO = new BoarderDAO();
            Boarder boarder = (Boarder) boarderDAO.getOne(user);
            RoomFeeBillDAO rfbdao = new RoomFeeBillDAO();
            RoomFeeBill bill = (RoomFeeBill)rfbdao.getOne(billid);
            BoardingInformationDAO bidao = new BoardingInformationDAO();
            BoardingInformation bi = bidao.getBoardingInformation(boarder.getBoarderID());
            WalletDAO walletDAO = new WalletDAO();
            Wallet wallet = (Wallet) walletDAO.getOne(user);
            ElectricAndWaterBillDAO eawbdao = new ElectricAndWaterBillDAO();
            NoticeDAO ndao = new NoticeDAO();
            DomManagerDAO dmdao = new DomManagerDAO();
            if (type == 1) {
                System.out.println("RoomFee");
                if (wallet.getBalance() < bi.getRoom().getCategory().getRoomFee()) {
                    request.getRequestDispatcher("ErrorPageBoarder.jsp").forward(request, response);
                } else {
                    System.out.println("Paying");
                    rfbdao.payBill(billid);
                    walletDAO.pay(boarder, bi.getRoom().getCategory().getRoomFee());
                    ndao.insert(new Notice(0, new Time(System.currentTimeMillis()), new Date(System.currentTimeMillis()), "You have successfully paid "+bi.getRoom().getCategory().getRoomFee()+" VND for the room in "+bill.getMonth(), boarder, (DomManager) dmdao.getAll().get(0), true));
                }
            }
            if (type == 2) {
                System.out.println("EWFee");
                ElectricAndWaterBill eawb = (ElectricAndWaterBill) eawbdao.getOne(billid);
                if (wallet.getBalance() < eawb.getEletricAmount() + eawb.getWaterAmount()) {
                    request.getRequestDispatcher("ErrorPageBoarder.jsp").forward(request, response);
                } else {
                    System.out.println("Paying");
                    eawbdao.payBill(billid);
                    System.out.println("Paying");
                    walletDAO.pay(boarder, eawb.getEletricAmount() + eawb.getWaterAmount());
                    System.out.println("Paying");
                    ndao.insert(new Notice(0, new Time(System.currentTimeMillis()), new Date(System.currentTimeMillis()), "You have successfully paid "+(eawb.getEletricAmount() + eawb.getWaterAmount())+" VND for electricity and water in "+eawb.getMonth(), boarder, (DomManager) dmdao.getAll().get(0), true));
                
                    System.out.println("Paying");}
            }
            request.setAttribute("page", "wallet");
            request.setAttribute("act", "pay");
            ArrayList<RoomFeeBill> rfb = rfbdao.getBills(boarder);
            request.setAttribute("boarding", bi);
            request.setAttribute("RoomFees", rfb);
            wallet = (Wallet)walletDAO.getOne(user);
            request.setAttribute("balance", wallet.getBalance());
            ArrayList<ElectricAndWaterBill> eawbs = eawbdao.getBoarderBills(bi.getRoom());
            request.setAttribute("ElectricWater", eawbs);
            request.getRequestDispatcher("boarderfee.jsp").forward(request, response);
        } catch (NumberFormatException | SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PayingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
