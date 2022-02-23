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
import model.Dom;
import model.DomManager;
import model.Management;

/**
 *
 * @author lenovo_thinkpad
 */
public class ManagementDAO extends Connection implements IBaseDAO {

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        ArrayList<Object> managements = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from Manager";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                DomDAO domDAO = new DomDAO();
                Dom dom = (Dom) domDAO.getOne(resultSet.getString(1)); //gọi phương thức getOne lấy về 1 dom theo domID
                DomManagerDAO dmd = new DomManagerDAO();
                DomManager domManager = (DomManager) dmd.getOne(resultSet.getInt(2)); ////gọi phương thức getOne lấy về 1 domManager theo ID
                Management management = new Management(dom, domManager);
                managements.add(management); // add vào list
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return managements;
    }

    @Override
    public Object getOne(Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getManagement(Object domId, Object managerId) throws SQLException {
        ArrayList<Object> managements = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from Manager where ? = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            if (domId == null) { //tìm tất cả các dom mà managerId ở trên quản lý
                preparedStatement.setString(1, "ManagerID"); //set dấu hỏi thứ nhất là 'managerID'
                preparedStatement.setInt(2, (int) managerId); //set dấu hỏi thức 2 là giá trị của ID đó
                resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
                // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
                while (resultSet.next()) {
                    DomDAO domDAO = new DomDAO();
                    Dom dom = (Dom) domDAO.getOne(resultSet.getString(1)); //gọi phương thức getOne lấy về 1 dom theo domID
                    DomManagerDAO dmd = new DomManagerDAO();
                    DomManager domManager = (DomManager) dmd.getOne(resultSet.getInt(2)); ////gọi phương thức getOne lấy về 1 domManager theo ID
                    Management management = new Management(dom, domManager);
                    managements.add(management); // add vào list
                }
                return managements;
            } else if (managerId == null) { //tìm tất cả các managerId trên quản lý domId trên
                preparedStatement.setString(1, "DomID"); //set dấu hỏi thứ nhất là 'DomID'
                preparedStatement.setString(2, (String) domId); //set dấu hỏi thức 2 là giá trị của ID đó
                resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
                while (resultSet.next()) {
                    DomDAO domDAO = new DomDAO();
                    Dom dom = (Dom) domDAO.getOne(resultSet.getString(1)); //gọi phương thức getOne lấy về 1 dom theo domID
                    DomManagerDAO dmd = new DomManagerDAO();
                    DomManager domManager = (DomManager) dmd.getOne(resultSet.getInt(2)); ////gọi phương thức getOne lấy về 1 domManager theo ID
                    Management management = new Management(dom, domManager);
                    managements.add(management); // add vào list
                }
                return managements;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void insert(Object object) throws SQLException {
        Management inserted = (Management) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Insert into Manager(ManagerID, DomID) values (?, ?)";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, inserted.getDomManager().getManagerID());
            preparedStatement.setString(2, inserted.getDom().getDomID());
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
        Management inserted = (Management) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Delete Manager where ManagerID = ? and DomID = ? ";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, inserted.getDomManager().getManagerID());
            preparedStatement.setString(2, inserted.getDom().getDomID());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
