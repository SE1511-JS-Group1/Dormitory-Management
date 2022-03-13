package controller.staff;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dao.impl.BoarderDAO;
import dao.impl.BoardingInformationDAO;
import dao.impl.DomDAO;
import dao.impl.DomManagerDAO;
import dao.impl.NoticeDAO;
import dao.impl.RoomDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Boarder;
import model.BoardingInformation;
import model.DomManager;
import model.Jobs;
import model.Notice;
import model.Room;
import org.apache.axis.encoding.Base64;

/**
 *
 * @author Dell
 */
public class EditBoarderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            int boarderId = Integer.parseInt(request.getParameter("boarderid"));
            BoarderDAO boarderDAO = new BoarderDAO();
            Boarder b = (Boarder) boarderDAO.getBoarderById(boarderId);
            NoticeDAO noticeDAO = new NoticeDAO();
            Notice notice;
            String mesString;
            DomManagerDAO dmdao = new DomManagerDAO();
            if (request.getParameter("act").equalsIgnoreCase("deny")) {
                mesString = "Your request to correct your personal information is not accepted! Please check your personal information!";
            } else {
                Cookie[] cookies = request.getCookies();
                if (cookies.length >= 2) {
                    for (Cookie c : cookies) {
                        if (c.getName().startsWith("Edit")) {
                            //Decode req
                            byte[] decodeBytes = Base64.decode(c.getValue());
                            String editReq = new String(decodeBytes, "UTF-8");
                            System.out.println(editReq);
                            String[] editing = editReq.split("\\|");
                            b = new Boarder(Integer.parseInt(editing[0]), editing[1], Date.valueOf(editing[3]), editing[2].equals("1"), editing[5], editing[4], Jobs.valueOf(editing[6]), b.getAccount());
                        }
                    }
                }
                boarderDAO.update(b, b.getBoarderID());
                mesString = "Your request to correct your personal information has been accepted! Please check your personal information!";
            }
            notice = new Notice(0, new Time(System.currentTimeMillis()), new Date(System.currentTimeMillis()), mesString, b, (DomManager) dmdao.getOne(((Account) request.getSession().getAttribute("account")).getUserName()), true);
            noticeDAO.insert(notice);
            Cookie[] cookies = request.getCookies();
            for (Cookie c : cookies) {
                if (c.getName().equals("Edit" + b.getBoarderID())) {
                    System.out.println("Xoa Cookie");
                    c.setPath(request.getContextPath());
                    c.setMaxAge(0);
                    response.addCookie(c);
                    break;
                }
            }
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
                boarderDAO = new BoarderDAO();
                ArrayList<BoardingInformation> boardingInformations = new ArrayList<>();
                ArrayList<Boarder> boarders = new ArrayList<>();
                cookies = request.getCookies();
                if (cookies.length >= 2) {
                    for (Cookie c : cookies) {
                        System.out.println(c.getMaxAge());
                        if (c.getName().startsWith("Book") && c.getMaxAge() != 0) {
                            String[] reqString = c.getValue().split("\\D");
                            System.out.printf("%s     %s        %s", reqString[0], reqString[1], reqString[2]);
                            Room r = (Room) roomDAO.getOne(Integer.parseInt(reqString[1]));
                            b = (Boarder) boarderDAO.getBoarderById(Integer.parseInt(reqString[0]));
                            BoardingInformation bi = new BoardingInformation(r, b, Integer.parseInt(reqString[2]), null, null);
                            if (r.getDom().getDomID().equalsIgnoreCase(domID)) {
                                boardingInformations.add(bi);
                            }
                        }
                        if (c.getName().startsWith("Edit") && c.getMaxAge() != 0) {
                            //Decode req
                            byte[] decodeBytes = Base64.decode(c.getValue());
                            String editReq = new String(decodeBytes, "UTF-8");
                            System.out.println(editReq);
                            String[] editing = editReq.split("\\|");
                            b = new Boarder(Integer.parseInt(editing[0]), editing[1], Date.valueOf(editing[3]), editing[2].equals("1"), editing[5], editing[4], Jobs.valueOf(editing[6]), null);
                            boarders.add(b);
                        }
                    }
                }
                BoarderDAO bdao = new BoarderDAO();
                request.setAttribute("bdao", bdao);
                request.setAttribute("editing", boarders);
                request.setAttribute("boarding", boardingInformations);
                request.getRequestDispatcher("ViewRequest.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(StaffBoarderManage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditBoarderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
