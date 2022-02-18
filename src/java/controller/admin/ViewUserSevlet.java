/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import controller.ForgotPasswordSevrvlet;
import dao.AccountDAO;
import dao.BoarderDAO;
import dao.DomManagerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Boarder;
import model.Dom;
import model.DomManager;
import model.Management;

/**
 *
 * @author Dell
 */
public class ViewUserSevlet extends HttpServlet {

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
        AccountDAO accountDAO = new AccountDAO();
        BoarderDAO boarderDAO = new BoarderDAO();
        DomManagerDAO domManagerDAO = new DomManagerDAO();
        ArrayList<Object> accounts = accountDAO.getAll();
        request.setAttribute("accounts", accounts);
        request.setAttribute("boarderDAO", boarderDAO);
        request.setAttribute("domManagerDAO", domManagerDAO);
        request.getRequestDispatcher("user_view_admin.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        AccountDAO accountDAO = new AccountDAO();
        Account account = (Account) accountDAO.getOne(username);
        String email;
        String name;
        if (account.getRole() == 2) {
            DomManagerDAO domManagerDAO = new DomManagerDAO();
            DomManager domManager = (DomManager) domManagerDAO.getOne(username);
            email = domManager.getEmail();
            name = domManager.getName();
        } else {
            BoarderDAO boarderDAO = new BoarderDAO();
            Boarder boarder = (Boarder) boarderDAO.getOne(username);
            email = boarder.getEmail();
            name = boarder.getBoarderName();
        }
        accountDAO.delete(account);
        System.out.println("Xoas thanh cong");
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    String username = "dormitory.swp@gmail.com";
                    String password = "dormitory1511";
                    return new PasswordAuthentication(username, password);
                }
            });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("dormitory.swp@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Remove from system");
            message.setText("Hello " + name + "\n"
                    + "We're sorry, your account has been removed from the system.\n"
                    + "You can re-register to continue accessing"
            );
            //            message.setReplyTo(message.getFrom());
            Transport.send(message);

        } catch (MessagingException e) {
            Logger.getLogger(ForgotPasswordSevrvlet.class.getName()).log(Level.SEVERE, null, e);
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
