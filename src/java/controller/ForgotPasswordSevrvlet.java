/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dao.BoarderDAO;
import dao.DomManagerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userName = request.getParameter("username");
        AccountDAO accountDAO = new AccountDAO();
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
                out.print("<html>\n"
                        + "    <head>\n"
                        + "        <title>Dormitory Management System</title>\n"
                        + "        <meta charset=\"UTF-8\">\n"
                        + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n"
                        + "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\n"
                        + "        <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js\" integrity=\"sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB\" crossorigin=\"anonymous\"></script>\n"
                        + "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js\" integrity=\"sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13\" crossorigin=\"anonymous\"></script>\n"
                        + "        <link rel='icon' href='https://by.com.vn/xQTXSg'>   \n"
                        + "        <link href=\"css/overview.css\" rel=\"stylesheet\">\n"
                        + "    </head>\n"
                        + "    <body>\n"
                        + "<div style=\"position: fixed;left: 0;top:  0;height: 100px;width: 100%;z-index: 2;\">"
                        + "        <header class=\"p-3 bg-dark text-white\">\n"
                        + "            <div class=\"container\">\n"
                        + "                <div class=\"d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start\">\n"
                        + "                    <a href=\"#\" class=\"d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none\">\n"
                        + "                        <div class=\"logo\" style=\"width: 70px;\">                            \n"
                        + "                            <img src=\"https://by.com.vn/xQTXSg\" class=\"img-thumbnail\" alt=\"logo\">\n"
                        + "                        </div>\n"
                        + "                        <div style=\"width: 20px;\"></div>\n"
                        + "                    </a>\n"
                        + "                    <ul class=\"nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0\">\n"
                        + "                        <li><a href=\"home\" class=\"nav-link px-2 text-secondary\">Home</a></li>\n"
                        + "                        <li><a href=\"#\" class=\"nav-link px-2 text-secondary\">Dom</a></li>\n"
                        + "                        <li><a href=\"#\" class=\"nav-link px-2 text-secondary\">Room</a></li>\n"
                        + "                        <li><a href=\"#\" class=\"nav-link px-2 text-secondary\">Contact</a></li>\n"
                        + "                        <li><a href=\"#\" class=\"nav-link px-2 text-secondary\">About</a></li>\n"
                        + "                    </ul>\n"
                        + "                    <div class=\"text-end\">\n"
                        + "                        <a type=\"button\" class=\"btn btn-outline-light me-2\" href=\"login\">Login</a>\n"
                        + "                        <a type=\"button\" class=\"btn btn-warning\" href=\"register\">Sign-up</a>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "        </header>"
                        + "</div>"
                        + " <section class=\"vh-100\" style=\"background-color: #00FFFF;\">\n"
                        + "                <div class=\"container py-5 h-100\">\n"
                        + "                    <div class=\"row d-flex justify-content-center align-items-center h-100\">\n"
                        + "                        <div class=\"col col-xl-4\">\n"
                        + "                            <div class=\"card\" style=\"border-radius: 1rem;\">\n"
                        + "                                <div class=\"row g-0\">\n"
                        + "                                    <div class=\"col-md-12 col-lg-12 d-flex align-items-center\">\n"
                        + "                                        <div class=\"card-body p-4 p-lg-5 text-black\">\n"
                        + "                                                <h6 class=\"mb-0 me-4 text-center\">Check your email to get new password! </h6>\n"
                        + "                                    </div>\n"
                        + "                                </div>\n"
                        + "                            </div>\n"
                        + "                        </div>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "        </section>\n"
                        + "<div style=\"position: fixed;left: 0;bottom: 0;height: 100px;width: 100%;z-index: 2;border-top: 1px dotted black;background-color:#ffffff;\">\n"
                        + "     <div class=\"container\">\n"
                        + "            <footer class=\"py-3 my-4\">\n"
                        + "                <ul class=\"nav justify-content-center border-bottom pb-3 mb-3\">\n"
                        + "                    <li class=\"nav-item\"><a href=\"home\" class=\"nav-link px-2 text-muted\">Home</a></li>\n"
                        + "                    <li class=\"nav-item\"><a href=\"#\" class=\"nav-link px-2 text-muted\">Dom</a></li>\n"
                        + "                    <li class=\"nav-item\"><a href=\"#\" class=\"nav-link px-2 text-muted\">Room</a></li>\n"
                        + "                    <li class=\"nav-item\"><a href=\"#\" class=\"nav-link px-2 text-muted\">Contact</a></li>\n"
                        + "                    <li class=\"nav-item\"><a href=\"#\" class=\"nav-link px-2 text-muted\">About</a></li>\n"
                        + "                </ul>\n"
                        + "                <p class=\"text-center text-muted\">© 2021 Company, Inc</p>\n"
                        + "            </footer>\n"
                        + "        </div>\n"
                        + "        </div>\n"
                        + "    </body>\n"
                        + "</html>");
                
            } catch (Exception e) {
                Logger.getLogger(ForgotPasswordSevrvlet.class.getName()).log(Level.SEVERE, null, e);
            }
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
