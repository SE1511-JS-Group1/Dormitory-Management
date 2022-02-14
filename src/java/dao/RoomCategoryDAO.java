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
import model.RoomCategory;

/**
 *
 * @author lenovo_thinkpad
 */
public class RoomCategoryDAO implements IBaseDAO {
    
    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> Categories = new ArrayList<>();
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "SELECT * FROM RoomCategory";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (Result.next()) {
                RoomCategory roomCategory = new RoomCategory(Result.getInt("CategoryID"), // tạo mợi object của mình và bắt add vào list
                        Result.getString("CategoryName"),
                        Result.getBoolean("Gender"),
                        Result.getDouble("RoomFee"),
                        Result.getInt("BedNumber"));
                Categories.add(roomCategory); // add vào list
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return Categories;
    }
    
    @Override
    public Object getOne(Object key) {
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "SELECT * FROM RoomCategory WHERE CategoryID = ?";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setInt(1, (int)key);
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (Result.next()) {
                RoomCategory roomCategory = new RoomCategory(Result.getInt("CategoryID"), // tạo mợi object của mình và bắt add vào list
                        Result.getString("CategoryName"),
                        Result.getBoolean("Gender"),
                        Result.getDouble("RoomFee"),
                        Result.getInt("BedNumber"));
                return roomCategory;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return null;
    }
    
    @Override
    public void insert(Object object) {
        RoomCategory inserted = (RoomCategory) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Insert into RoomCategory(CategoryID,CategoryName,Gender,RoomFee,BedNumber) values (?,?,?,?,?)";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setInt(1, inserted.getCategoryID());
            Statement.setString(2, inserted.getCategoryName());
            Statement.setBoolean(3, inserted.isRoomGender());
            Statement.setDouble(4, inserted.getRoomFee());
            Statement.setInt(5, inserted.getBedNumber());
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
        RoomCategory deleted = (RoomCategory) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Delete RoomCategory where CategoryID = ?";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setInt(1, deleted.getCategoryID());
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
        RoomCategory updated = (RoomCategory) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Update RoomCategory set CategoryName = ?, Gender = ?, RoomFee = ?, BedNumber = ?  where CategoryID = ? ";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setString(1, updated.getCategoryName());
            Statement.setBoolean(2, updated.isRoomGender());
            Statement.setDouble(3, updated.getRoomFee());
            Statement.setInt(4, updated.getBedNumber());
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
    
}
