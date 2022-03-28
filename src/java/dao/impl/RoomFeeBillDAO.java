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

import dao.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Boarder;
import model.RoomFeeBill;

/**
 *
 * @author lenovo_thinkpad
 */
public class RoomFeeBillDAO extends Connection implements IBaseDAO{

    @Override
  public ArrayList<Object> getAll() throws SQLException {
        ArrayList<Object> roomfeebill = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM RoomFeeBill";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            BoarderDAO boarderDAO = new BoarderDAO();
            while (resultSet.next()) {
                RoomFeeBill roomlbill = new RoomFeeBill(resultSet.getInt("BillID"), 
                        boarderDAO.getBoarderById(resultSet.getInt("BoarderID")), 
                        resultSet.getString("Month"), 
                        resultSet.getDate("Deadline"), 
                        resultSet.getBoolean("Status"));
                roomfeebill.add(roomlbill);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return roomfeebill;
    }
    public ArrayList<RoomFeeBill> getBills(Boarder boarder) throws SQLException{
        ArrayList<RoomFeeBill> feeBills = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM RoomFeeBill where BoarderID = ? and [Status] = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, boarder.getBoarderID());
            preparedStatement.setBoolean(2, false);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            BoarderDAO boarderDAO = new BoarderDAO();
            while (resultSet.next()) {
                RoomFeeBill roomlbill = new RoomFeeBill(resultSet.getInt("BillID"), 
                        boarderDAO.getBoarderById(resultSet.getInt("BoarderID")), 
                        resultSet.getString("Month"), 
                        resultSet.getDate("Deadline"), 
                        resultSet.getBoolean("Status"));
                feeBills.add(roomlbill);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return feeBills;
    }
    @Override
    public Object getOne(Object key) throws SQLException{
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM RoomFeeBill WHERE billID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, (int) key);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            BoarderDAO boarderDAO = new BoarderDAO();
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                RoomFeeBill account = new RoomFeeBill(resultSet.getInt("BillID"), 
                        boarderDAO.getBoarderById(resultSet.getInt("BoarderID")), 
                        resultSet.getString("Month"), 
                        resultSet.getDate("Deadline"), 
                        resultSet.getBoolean("Status"));
                return account; 
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
    public void insert(Object object) throws SQLException{
        RoomFeeBill inserted = (RoomFeeBill) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO RoomFeeBill (BillID,Deadline,Status,Month,BoarderID)VALUES(?,?,?,?,?)";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1,inserted.getBillID());
            preparedStatement.setDate(1, inserted.getDeadline());
            preparedStatement.setBoolean(2, inserted.getStatus());
            preparedStatement.setString(3, inserted.getMonth());
            preparedStatement.setInt(4, inserted.getBoarder().getBoarderID());
            
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void delete(Object object) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object object, Object key) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void payBill(int billId) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "Update RoomFeeBill set [Status] = ? where BillId = ? ";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, billId);
            preparedStatement.setBoolean(2, true);
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }
}
