/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.staff;

import dao.impl.BoarderDAO;
import dao.impl.BoardingInformationDAO;
import dao.impl.DomDAO;
import dao.impl.DomManagerDAO;
import dao.impl.NoticeDAO;
import dao.impl.RoomDAO;
import dao.impl.RoomStatusDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Boarder;
import model.BoardingInformation;
import model.DomManager;
import model.Notice;
import model.Room;

/**
 *
 * @author Dell
 */
public class ViewRequestServlet extends HttpServlet {

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
        BoardingInformationDAO dao = new BoardingInformationDAO();
        DomDAO domDAO = new DomDAO();
        try {
            request.setAttribute("doms", domDAO.getAll());
            String domID = request.getParameter("dom") == null ? "A" : request.getParameter("dom");
            Object dom = domDAO.getOne(domID);
            request.setAttribute("dom", dom);
            request.setAttribute("page", "boarder");
            // Doc request tu cookie
            RoomDAO roomDAO = new RoomDAO();
            BoarderDAO boarderDAO = new BoarderDAO();
            ArrayList<BoardingInformation> boardingInformations = new ArrayList<>();
            Cookie[] cookies = request.getCookies();
            if (cookies.length >= 2) {
                for (Cookie c : cookies) {
                    if (c.getName().startsWith("Book")) {
                        String[] reqString = c.getValue().split("\\D");
                        System.out.printf("%s     %s        %s", reqString[0], reqString[1], reqString[2]);
                        Room r = (Room) roomDAO.getOne(Integer.parseInt(reqString[1]));
                        Boarder b = (Boarder) boarderDAO.getBoarderById(Integer.parseInt(reqString[0]));
                        BoardingInformation bi = new BoardingInformation(r, b, Integer.parseInt(reqString[2]), null, null);
                        if (r.getDom().getDomID().equalsIgnoreCase(domID)) {
                            boardingInformations.add(bi);
                        }
                    }
                }
            }
            System.out.println("Size: "+boardingInformations.size());
            request.setAttribute("boarding", boardingInformations);
            request.getRequestDispatcher("ViewRequest.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(StaffBoarderManage.class.getName()).log(Level.SEVERE, null, ex);
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
            int boarderId = Integer.parseInt(request.getParameter("boarderid"));
            int roomId = Integer.parseInt(request.getParameter("roomid"));
            int bedNo = Integer.parseInt(request.getParameter("bedno"));
            String action = request.getParameter("act");
            BoarderDAO boarderDAO = new BoarderDAO();
            RoomDAO roomDAO = new RoomDAO();
            Boarder b = (Boarder) boarderDAO.getBoarderById(boarderId);
            Room r = (Room) roomDAO.getOne(roomId);
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
            NoticeDAO noticeDAO = new NoticeDAO();
            Notice notice;
            DomManagerDAO dmdao = new DomManagerDAO();
            String mesString;
            String email = b.getEmail();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("dormitory.swp@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            if (action.equalsIgnoreCase("accept")) {
                RoomStatusDAO roomStatusDAO = new RoomStatusDAO();
                roomStatusDAO.setStatusNewBoarder(r, bedNo);
                BoardingInformation boardingInformation = new BoardingInformation(r, b, bedNo, new Date(System.currentTimeMillis()), null);
                BoardingInformationDAO bidao = new BoardingInformationDAO();
                bidao.insert(boardingInformation);
                message.setSubject("Succes Booking");
                message.setText("Your booking request has been accepted.\n Information: " + r.getDom().getDomName() + " Room " + r.getRoomName() + " Bed " + bedNo + " Please complete the fee to use the services of the dormitory system! Thank you!");
                Transport.send(message);
                mesString = "Your booking request has been accepted.\n Information: " + r.getDom().getDomName() + " Room " + r.getRoomName() + " Bed " + bedNo + " Please complete the fee to use the services of the dormitory system! Thank you!";
            } else {
                message.setSubject("Error Booking");
                message.setText("Your booking request has been denied. Maybe you were a step behind or the gender didn't match! Thank you!");
                Transport.send(message);
                mesString = "Your booking request has been denied. Maybe you were a step behind or the gender didn't match! Thank you!";
            }
            notice = new Notice(0, new Time(System.currentTimeMillis()),new Date(System.currentTimeMillis()), mesString, b, (DomManager) dmdao.getOne(((Account) request.getSession().getAttribute("account")).getUserName()), true);
            noticeDAO.insert(notice);
            Cookie[] cookies = request.getCookies();
            for (Cookie c : cookies) {
                if (c.getName().equals("Book" + b.getBoarderID())) {
                    System.out.println("Xoa Cookie");
                    c.setPath(request.getContextPath());
                    c.setMaxAge(0);
                    response.addCookie(c);
                    break;
                }
            }
            DomDAO domDAO = new DomDAO();
            request.setAttribute("doms", domDAO.getAll());
            String domID = r.getDom().getDomID();
            Object dom = domDAO.getOne(domID);
            request.setAttribute("dom", dom);
            request.setAttribute("page", "boarder");
            // Doc request tu cookie
            ArrayList<BoardingInformation> boardingInformations = new ArrayList<>();
            if (cookies.length >= 2) {
                for (Cookie c : cookies) {
                    if (c.getName().startsWith("Book") && c.getMaxAge() > 0) {
                        System.out.println("Max Age: " + c.getMaxAge());
                        String[] reqString = c.getValue().split("\\D");
                        System.out.printf("%s     %s        %s", reqString[0], reqString[1], reqString[2]);
                        Room ro = (Room) roomDAO.getOne(Integer.parseInt(reqString[1]));
                        Boarder bo = (Boarder) boarderDAO.getBoarderById(Integer.parseInt(reqString[0]));
                        BoardingInformation bi = new BoardingInformation(ro, bo, Integer.parseInt(reqString[2]), null, null);
                        if (r.getDom().getDomID().equalsIgnoreCase(domID)) {
                            boardingInformations.add(bi);
                        }
                    }
                }
            }
            request.setAttribute("boarding", boardingInformations);
            request.getRequestDispatcher("ViewRequest.jsp").forward(request, response);
        } catch (SQLException | MessagingException ex) {
            Logger.getLogger(ViewRequestServlet.class.getName()).log(Level.SEVERE, null, ex);
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
