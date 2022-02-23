/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * DAO Implement
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-23      1.2                 DucHT           Update code
 */
package dao.impl;

import dao.Connection;
import dao.IBaseDAO;
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
public class RoomDAO extends Connection implements IBaseDAO {

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        ArrayList<Object> rooms = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Room";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            RoomCategoryDAO roomCategoryDAO = new RoomCategoryDAO();
            DomDAO domDAO = new DomDAO();
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                Room room = new Room(resultSet.getInt(1), // tạo mợi object của mình và bắt add vào list
                        (Dom) domDAO.getOne(resultSet.getString(4)),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        (RoomCategory) roomCategoryDAO.getOne((int) resultSet.getInt(5)));
                rooms.add(room); // add vào list
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return rooms;
    }

    @Override
    public Object getOne(Object key) throws SQLException {
        Object room = new Room();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Room where RoomID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, (int) key);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            RoomCategoryDAO roomCategoryDAO = new RoomCategoryDAO();
            DomDAO domDAO = new DomDAO();
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                room = new Room(resultSet.getInt(1), // tạo mợi object của mình và bắt add vào list
                        (Dom) domDAO.getOne(resultSet.getString(4)),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        (RoomCategory) roomCategoryDAO.getOne((int) resultSet.getInt(5)));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return room;
    }

    @Override
    public void insert(Object object) throws SQLException {
        Room inserted = (Room) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Insert into Room(RoomID,RoomName,Floor,DomID,CategoryID) values (?,?,?,?,?)";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, inserted.getRoomID());
            preparedStatement.setString(2, inserted.getRoomName());
            preparedStatement.setInt(3, inserted.getFloor());
            preparedStatement.setString(4, inserted.getDom().getDomID());
            preparedStatement.setInt(5, inserted.getCategory().getCategoryID());
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void delete(Object object) throws SQLException {
        Room deleted = (Room) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Delete Room where RoomID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, deleted.getRoomID());
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void update(Object object, Object key) throws SQLException {
        Room updated = (Room) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Update Room set RoomName = ?, Floor = ?, DomID = ?, CategoryID = ?   where RoomID = ? ";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, updated.getRoomName());
            preparedStatement.setInt(2, updated.getFloor());
            preparedStatement.setString(3, updated.getDom().getDomID());
            preparedStatement.setInt(4, updated.getCategory().getCategoryID());
            preparedStatement.setInt(5, (int) key);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    public HashMap<Integer, Integer> getStatus() throws SQLException {
        HashMap<Integer, Integer> roomstatus = new HashMap<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select r.RoomID, rc.BedNumber\n"
                + "from room r, RoomCategory rc\n"
                + "where r.CategoryID = rc.CategoryID";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                roomstatus.put(resultSet.getInt(1), resultSet.getInt(2)); // add vào list
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return roomstatus;
    }
}
