/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import controller.ForgotPasswordSevrvlet;
import dao.AccountDAO;
import dao.DomDAO;
import dao.DomManagerDAO;
import dao.ManagementDAO;
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
import model.Dom;
import model.DomManager;
import model.Management;

/**
 *
 * @author Dell
 */
public class ViewAuthorizeServlet extends HttpServlet {

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
        DomManagerDAO domManagerDAO = new DomManagerDAO();
        DomDAO domDAO = new DomDAO();
        ArrayList<DomManager> notAuthorized = domManagerDAO.getNotAuthorizedStaff();
        request.setAttribute("doms", domDAO.getAll());
        request.setAttribute("notAuthorized", notAuthorized);
        request.setAttribute("domManagerDAO", domManagerDAO);
        request.getRequestDispatcher("authorize_view_admin.jsp").forward(request, response);
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
        String username = request.getParameter("user");
        String domId = request.getParameter("dom");
        response.getWriter().print(username + "," + domId);
        AccountDAO accountDAO = new AccountDAO();
        DomManagerDAO domManagerDAO = new DomManagerDAO();
        ManagementDAO managementDAO = new ManagementDAO();
        DomDAO domDAO = new DomDAO();
        DomManager domManager = (DomManager) domManagerDAO.getOne(username);
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
            String email = domManager.getEmail();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("dormitory.swp@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            if (domId == null) {
                // delete this staff in data
                domManagerDAO.delete(username);
                accountDAO.delete(username);
                message.setSubject("Deny staff authorization");
                message.setText("Hello " + domManager.getName() + "\n"
                        + "We regret to inform that your authorization request was not accepted.\n"
                );
            } else {
                domManagerDAO.makeAuthorize(username);
                Dom dom = (Dom) domDAO.getOne(domId);
                managementDAO.insert(new Management(dom, domManager));
                message.setSubject("Accept staff authorization");
                message.setText("Hello " + domManager.getName() + "\n"
                        + "Welcome to Dormitory Management System!\n"
                        + "You have been authorized as an " + domManager.getRegency() + " for the system, your current position will be " + dom.getDomName() + ".\n"
                        + "Now, You can access the system to work!");
            }
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
