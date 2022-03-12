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
import model.*;

/**
 *
 * @author lenovo_thinkpad
 */
public class ViolationDAO extends Connection implements IBaseDAO {

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        ArrayList<Object> vios = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from Violation";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                Violation vio = new Violation(resultSet.getInt(1),
                                            resultSet.getString(2),
                                            resultSet.getString(3),
                                            resultSet.getString(5),
                                            resultSet.getString(4));
                vios.add(vio);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return vios;
    }

    @Override
    public Object getOne(Object key) throws SQLException {
        return null;       
    }
    
    public Violation getViolationByID(int key) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from Violation where ViolationID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, key);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                Violation a = new Violation(key, resultSet.getString(2), resultSet.getString(3), 
                                    resultSet.getString(5), resultSet.getString(4));
                return a; // trả về đối tượng cần tìm
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
        Violation inserted = (Violation) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        String sql = "insert into Violation (Type, Violator, Penalization, Discription, ManagerID) values (\n" +
                        "?,?,?,?,?)";
        try {
            Connect = getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setString(1, inserted.getType());
            Statement.setInt(2, inserted.getViolationID());
            Statement.setString(3, inserted.getPenalization());
            Statement.setString(4, inserted.getDiscription());
            Statement.setInt(5, 1);
            Statement.executeUpdate(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closePreparedStatement(Statement);
            closeConnection(Connect);
        }
    }

    @Override
    public void delete(Object object) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "delete Violation where ViolationID = ?";
        try{
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, (int)object);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            throw e;
        }
    }

    @Override
    public void update(Object object, Object key) throws SQLException {
        Violation updated = (Violation) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        String sql = "update Violation set Type=?, Violator=?, Penalization=?, "
                + "Discription=? where ViolationID = ?";
        try{
            Connect = getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setString(1, updated.getType());
            Statement.setString(2, updated.getViolatorID());
            Statement.setString(3, updated.getPenalization());
            Statement.setString(4, updated.getDiscription());
            Statement.setInt(5, updated.getViolationID());
            Statement.executeUpdate();
        } catch(SQLException e) {
            throw e;
        }
    }

}
