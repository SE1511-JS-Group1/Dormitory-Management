/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * Controller Common
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-23      2.0                 DucHT           Update code
 */
package controller;

import dao.impl.AccountDAO;
import dao.impl.BoarderDAO;
import dao.impl.DomManagerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
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
import model.DomManager;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Admin
 */
public class ForgotPasswordSevrvlet extends HttpServlet {

    public String generateRandomPassword() {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*_-";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
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
        request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
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
    protected void doPost(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userName = request.getParameter("username");
        AccountDAO accountDAO = new AccountDAO();
        try {
            if (accountDAO.getOne(userName) == null) {

                request.setAttribute("message_forgotpassword", "Tài khoản không tồn tại");
                request.setAttribute("username", userName);
                request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
            } else {
                try (PrintWriter out = response.getWriter()) {
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
                    DomManagerDAO domManagerDAO = new DomManagerDAO();
                    BoarderDAO boarderDAO = new BoarderDAO();
                    String email = null;
                    if (((Account) accountDAO.getOne(userName)).getRole() == 2) {
                        email = ((DomManager) domManagerDAO.getOne(userName)).getEmail();
                    } else {
                        email = ((Boarder) boarderDAO.getOne(userName)).getEmail();
                    }
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("dormitory.swp@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                    message.setSubject("Reset password");
                    String newpass = generateRandomPassword();
                    message.setText("New Password: " + newpass);
//            message.setReplyTo(message.getFrom());
                    Transport.send(message);
                    accountDAO.update(newpass, userName);
                    request.getRequestDispatcher("waiting.jsp").forward(request, response);

                } catch (Exception e) {
                    Logger.getLogger(ForgotPasswordSevrvlet.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordSevrvlet.class.getName()).log(Level.SEVERE, null, ex);
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
