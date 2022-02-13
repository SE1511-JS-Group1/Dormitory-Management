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
import java.util.HashMap;
import model.Dom;
import model.Room;
import model.RoomCategory;

/**
 *
 * @author lenovo_thinkpad
 */
public class RoomDAO implements IBaseService {

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> rooms = new ArrayList<>();
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "SELECT * FROM Room";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            RoomCategoryDAO roomCategoryDAO = new RoomCategoryDAO();
            DomDAO domDAO = new DomDAO();
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (Result.next()) {
                Room room = new Room(Result.getInt(1), // tạo mợi object của mình và bắt add vào list
                        (Dom) domDAO.getOne(Result.getString(4)),
                        Result.getString(2),
                        Result.getInt(3),
                        (RoomCategory) roomCategoryDAO.getOne((int) Result.getInt(5)));
                rooms.add(room); // add vào list
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return rooms;
    }

    @Override
    public Object getOne(Object key) {
        Object room = new Room();
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "SELECT * FROM Room where RoomID = ?";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setInt(1, (int) key);
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            RoomCategoryDAO roomCategoryDAO = new RoomCategoryDAO();
            DomDAO domDAO = new DomDAO();
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (Result.next()) {
                room = new Room(Result.getInt(1), // tạo mợi object của mình và bắt add vào list
                        (Dom) domDAO.getOne(Result.getString(4)),
                        Result.getString(2),
                        Result.getInt(3),
                        (RoomCategory) roomCategoryDAO.getOne((int) Result.getInt(5)));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return room;
    }

    @Override
    public void insert(Object object) {
        Room inserted = (Room) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Insert into Room(RoomID,RoomName,Floor,DomID,CategoryID) values (?,?,?,?,?)";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setInt(1, inserted.getRoomID());
            Statement.setString(2, inserted.getRoomName());
            Statement.setInt(3, inserted.getFloor());
            Statement.setString(4, inserted.getDom().getDomID());
            Statement.setInt(5, inserted.getCategory().getCategoryID());
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
        Room deleted = (Room) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Delete Room where RoomID = ?";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setInt(1, deleted.getRoomID());
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
        Room updated = (Room) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Update Room set RoomName = ?, Floor = ?, DomID = ?, CategoryID = ?   where RoomID = ? ";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setString(1, updated.getRoomName());
            Statement.setInt(2, updated.getFloor());
            Statement.setString(3, updated.getDom().getDomID());
            Statement.setInt(4, updated.getCategory().getCategoryID());
            Statement.setInt(5, (int)key);
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
    }

    public HashMap<Integer, Integer> getStatus() {
        HashMap<Integer, Integer> roomstatus = new HashMap<>();
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "select r.RoomID, rc.BedNumber\n"
                + "from room r, RoomCategory rc\n"
                + "where r.CategoryID = rc.CategoryID";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (Result.next()) {
                roomstatus.put(Result.getInt(1), Result.getInt(2)); // add vào list
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return roomstatus;
    }
}
