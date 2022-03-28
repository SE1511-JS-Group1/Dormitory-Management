/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * DAO Implement
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-03-23      1.0                 DucHT            First Implement
 */
package dao.impl;

import dao.Connection;
import dao.IBaseDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ElectricAndWaterBill;
import model.Room;

/**
 *
 * @author Dell
 */
public class ElectricAndWaterBillDAO extends Connection implements IBaseDAO {

    @Override
    public ArrayList<Object> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getOne(Object key) throws Exception {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM ElectricAndWaterBill WHERE billID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, (int) key);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            RoomDAO rdao = new RoomDAO();
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                ElectricAndWaterBill account = new ElectricAndWaterBill(resultSet.getInt("BillID"),
                        (Room)rdao.getOne(resultSet.getInt("roomid")),
                        resultSet.getString("Month"),
                        resultSet.getDate("Deadline"),
                        resultSet.getDouble("WaterAmuont"),
                        resultSet.getDouble("ElectricAmount"),
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
    public void insert(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object object, Object key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void payBill(int billId) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "Update ElectricAndWaterBill set [Status] = ? where BillID = ? ";
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

    public ArrayList<ElectricAndWaterBill> getBoarderBills(Room room) throws SQLException {
        ArrayList<ElectricAndWaterBill> eawbs = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM ElectricAndWaterBill where RoomID = ? and [Status] = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, room.getRoomID());
            preparedStatement.setBoolean(2, false);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                ElectricAndWaterBill eawb = new ElectricAndWaterBill(resultSet.getInt("BillID"),
                        room,
                        resultSet.getString("Month"),
                        resultSet.getDate("Deadline"),
                        resultSet.getDouble("WaterAmuont"),
                        resultSet.getDouble("ElectricAmount"),
                        resultSet.getBoolean("Status"));
                eawbs.add(eawb);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return eawbs;
    }
}
