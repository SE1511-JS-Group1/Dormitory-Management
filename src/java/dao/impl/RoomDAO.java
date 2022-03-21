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
import java.sql.Date;
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
        String sql = "Insert into Room(RoomName,Floor,DomID,CategoryID) values (?,?,?,?)";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, inserted.getRoomName());
            preparedStatement.setInt(2, inserted.getFloor());
            preparedStatement.setString(3, inserted.getDom().getDomID());
            preparedStatement.setInt(4, inserted.getCategory().getCategoryID());
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
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
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
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
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
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

    public Room getFistRoom(Dom dom) throws SQLException {
        Room room = new Room();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT Top(1)* FROM Room where DomID = ? Order By RoomID desc";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, dom.getDomID());
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

    private Room getLastRoom(String domId, int floor) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT top(1) * FROM Room where floor = ? and domId = ? Order by roomID desc";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, floor);
            preparedStatement.setString(2, domId);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            RoomCategoryDAO roomCategoryDAO = new RoomCategoryDAO();
            DomDAO domDAO = new DomDAO();
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                return new Room(resultSet.getInt("RoomID"), // tạo mợi object của mình và bắt add vào list
                        (Dom) domDAO.getOne(resultSet.getString("DOmID")),
                        resultSet.getString("RoomName"),
                        resultSet.getInt("Floor"),
                        (RoomCategory) roomCategoryDAO.getOne((int) resultSet.getInt("CategoryID")));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return null;
    }

    public void addNewRoom(String domId, int floor, int category) throws SQLException {
        Room lastRoom = getLastRoom(domId, floor);
        if (lastRoom != null && lastRoom.getRoomName().endsWith("16")) {
            return;
        }
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "Insert into Room(RoomName,Floor,DomID,CategoryID) values (?,?,?,?)";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, lastRoom == null ? String.valueOf(floor * 100 + 1)
                    : String.valueOf(Integer.parseInt(lastRoom.getRoomName()) + 1));
            preparedStatement.setInt(2, floor);
            preparedStatement.setString(3, domId);
            preparedStatement.setInt(4, category);
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }

        RoomCategoryDAO rcdao = new RoomCategoryDAO();
        RoomCategory rc = (RoomCategory) rcdao.getOne(category);
        for (int i = 1; i <= rc.getBedNumber(); i++) {
            sql = "Insert into RoomStatus(RoomID,BedNo,Status) values (?,?,?) ";
            try {
                connection = getConnection(); // Open 1 connect với Database của mình
                preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
                preparedStatement.setInt(1, getLastRoom(domId, floor).getRoomID());
                preparedStatement.setInt(2, i);
                preparedStatement.setBoolean(3, false);
                preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
            } catch (SQLException e) {
                throw e;
            } finally {
                closePreparedStatement(preparedStatement);
                closeConnection(connection);
            }
        }
    }
    
    public void disableRoom(int roomID) throws SQLException{
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "Update BoardingInformation set EndDate = ? where RoomID = ? "
                + "Update RoomStatus set [Status] = ? where RoomID = ? ";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setDate(1, new Date(System.currentTimeMillis()));
            preparedStatement.setInt(2, roomID);
            preparedStatement.setBoolean(3, false);
            preparedStatement.setInt(4, roomID);
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }
    
     public Room getRoomById(int id) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from Room where RoomID = ?";
        
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int ID = resultSet.getInt("RoomID");
                String name = resultSet.getString("RoomName");
                int IDFloor = resultSet.getInt("Floor");
                DomDAO dom = new DomDAO();
                Dom a = (Dom) dom.getOne(resultSet.getInt("DomID"));
                RoomCategoryDAO categorydao = new RoomCategoryDAO();
                RoomCategory category = (RoomCategory) categorydao.getOne(resultSet.getInt("CategoryID"));
                Room room = new Room(ID, a, name, IDFloor, category);
                return room;
            }
            
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return null;
    }
}
