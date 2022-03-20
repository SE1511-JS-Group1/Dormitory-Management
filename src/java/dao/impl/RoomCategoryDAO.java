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
import model.RoomCategory;

/**
 *
 * @author lenovo_thinkpad
 */
public class RoomCategoryDAO extends Connection implements IBaseDAO {

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        ArrayList<Object> Categories = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM RoomCategory";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                RoomCategory roomCategory = new RoomCategory(resultSet.getInt("CategoryID"), // tạo mợi object của mình và bắt add vào list
                        resultSet.getString("CategoryName"),
                        resultSet.getBoolean("Gender"),
                        resultSet.getDouble("RoomFee"),
                        resultSet.getInt("BedNumber"));
                Categories.add(roomCategory); // add vào list
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return Categories;
    }

    @Override
    public Object getOne(Object key) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM RoomCategory WHERE CategoryID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, (int) key);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                RoomCategory roomCategory = new RoomCategory(resultSet.getInt("CategoryID"), // tạo mợi object của mình và bắt add vào list
                        resultSet.getString("CategoryName"),
                        resultSet.getBoolean("Gender"),
                        resultSet.getDouble("RoomFee"),
                        resultSet.getInt("BedNumber"));
                return roomCategory;
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

    @Override
    public void insert(Object object) throws SQLException {
        RoomCategory inserted = (RoomCategory) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Insert into RoomCategory(CategoryID,CategoryName,Gender,RoomFee,BedNumber) values (?,?,?,?,?)";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, inserted.getCategoryID());
            preparedStatement.setString(2, inserted.getCategoryName());
            preparedStatement.setBoolean(3, inserted.isRoomGender());
            preparedStatement.setDouble(4, inserted.getRoomFee());
            preparedStatement.setInt(5, inserted.getBedNumber());
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
        RoomCategory deleted = (RoomCategory) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Delete RoomCategory where CategoryID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, deleted.getCategoryID());
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
        RoomCategory updated = (RoomCategory) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Update RoomCategory set CategoryName = ?, Gender = ?, RoomFee = ?, BedNumber = ?  where CategoryID = ? ";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, updated.getCategoryName());
            preparedStatement.setBoolean(2, updated.isRoomGender());
            preparedStatement.setDouble(3, updated.getRoomFee());
            preparedStatement.setInt(4, updated.getBedNumber());
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
    
      public RoomCategory getRoomCategoryById(int id) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from RoomCategory where CategoryID = ?";
        
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int ID = resultSet.getInt("CategoryID"); 
                String name = resultSet.getString("CategoryName"); 
                boolean gender = resultSet.getBoolean("Gender");
                float roomfee = resultSet.getFloat("RoomFee");
                int bedNumber = resultSet.getInt("BedNumber"); 
                RoomCategory category = new RoomCategory(ID, name, gender, roomfee, bedNumber);
                return category;
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
