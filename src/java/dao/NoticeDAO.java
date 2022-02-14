/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Boarder;
import model.DomManager;
import model.Notice;

/**
 *
 * @author lenovo_thinkpad
 */
public class NoticeDAO implements IBaseDAO{

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> notices = new ArrayList<>();
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "select * from Notices";        
        System.out.println(Connection.getConnection());
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (Result.next()) {
                int boarderID = Result.getInt(5); // lấy id của boarder từ database             
                BoarderDAO boarderDAO = new BoarderDAO();
                Boarder boarder = boarderDAO.getBoarderById(boarderID); //lấy object boarder thông qua phương thức getOne trong BoarderDAO
                
                int ManagerID = Result.getInt(6);  //lấy id của domManager từ database
                DomManagerDAO dmDAO = new DomManagerDAO();
                DomManager domManager = (DomManager) dmDAO.getOne(ManagerID); //lấy object DomManager thông qua phương thức getOne trong DomManagerDAO
                Notice notice = new Notice(Result.getInt("NoticeID"), //tạo mới một notice
                                            Result.getDate("Time"),
                                            Result.getString("Title"),
                                            boarder,
                                            domManager,
                                            Result.getBoolean("Direction"));
                notices.add(notice);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return notices;
    }

    @Override
    public Object getOne(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Object object) {
        Notice inserted = (Notice) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Insert into Notices (NoticeID, Direction, Time, Title, BoarderID, ManagerID) values (?,?,?,?,?,?)";
        System.out.println(Connection.getConnection());
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setInt(1, inserted.getId());
            Statement.setBoolean(2, inserted.isDirection());
            Statement.setDate(3, inserted.getTimeSend());
            Statement.setString(4, inserted.getTitle());
            Statement.setInt(5, inserted.getBoarder().getBoarderID());
            Statement.setInt(6, inserted.getDomManager().getManagerID());
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
    }

    @Override
    public void delete(Object object) {
        Notice deleted = (Notice) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Delete Notices where NoticeID = ?";
        System.out.println(Connection.getConnection());
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setInt(1, deleted.getId());
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
    }

    @Override
    public void update(Object object, Object key) {
        Notice updated = (Notice) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Update Notices set Direction= ?, Time= ?, Title= ?, BoarderID= ?, ManagerID= ? "
                + "where NoticeID=?";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setBoolean(1, updated.isDirection());
            Statement.setString(2, updated.getTimeSend().toString());
            Statement.setString(3, updated.getTitle());
            Statement.setInt(4, updated.getBoarder().getBoarderID());
            Statement.setInt(5, updated.getDomManager().getManagerID());
            Statement.setInt(6, (int) key); //key là giá trị id của Notice cần thay đổi
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
    }
    
}
