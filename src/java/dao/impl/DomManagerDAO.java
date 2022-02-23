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
import model.Account;
import model.DomManager;
import model.ManagerRegency;

/**
 *
 * @author lenovo_thinkpad
 */
public class DomManagerDAO extends Connection implements IBaseDAO {

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        ArrayList<Object> domManagers = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        java.sql.Connection connection = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM DomManager";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            AccountDAO accountDAO = new AccountDAO();
            while (resultSet.next()) {
                DomManager domManager = new DomManager(resultSet.getInt("ManagerID"), // tạo mợi object của mình và bắt add vào list
                        resultSet.getString("ManagerName"),
                        resultSet.getBoolean("Gender"),
                        resultSet.getDate("DOB"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber"),
                        ManagerRegency.valueOf(resultSet.getString("Regency")),
                        (Account) accountDAO.getOne(resultSet.getString("UserName")));
                domManagers.add(domManager); // add vào list
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return domManagers;
    }

    @Override
    public Object getOne(Object key) throws SQLException { //get về 1 object dom manager thông qua dommanager username
        Object domManager = new DomManager();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM DomManager where UserName = ? ";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, (String) key);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            AccountDAO accountDAO = new AccountDAO();
            while (resultSet.next()) {
                domManager = new DomManager(resultSet.getInt("ManagerID"), // tạo mợi object của mình và bắt add vào list
                        resultSet.getString("ManagerName"),
                        resultSet.getBoolean("Gender"),
                        resultSet.getDate("DOB"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber"),
                        ManagerRegency.valueOf(resultSet.getString("Regency")),
                        (Account) accountDAO.getOne(resultSet.getString("UserName")));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return domManager;
    }

    @Override
    public void insert(Object object) throws SQLException {
        DomManager inserted = (DomManager) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Insert into DomManager(PhoneNumber, ManagerName, Email, Gender, DOB, Regency, UserName)\n"
                + "values(?,?,?,?,?,?,?)";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
//            Statement.setInt(1, inserted.getManagerID());
            preparedStatement.setString(1, inserted.getPhoneNumber());
            preparedStatement.setString(2, inserted.getName());
            preparedStatement.setString(3, inserted.getEmail());
            preparedStatement.setBoolean(4, inserted.isGender());
            preparedStatement.setDate(5, inserted.getDateOfBirth());
            preparedStatement.setString(6, inserted.getRegency().toString());
            preparedStatement.setString(7, inserted.getAccount().getUserName());
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
    public void delete(Object key) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DomManager domManager = (DomManager) getOne(key);
        String sql = "Delete DomManager where UserName = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, (String) key);
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
        DomManager updated = (DomManager) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Update DomManager set \n"
                + "PhoneNumber=?, \n"
                + "ManagerName=?, \n"
                + "Email=?,\n"
                + "Gender=?,\n"
                + "DOB=?,\n"
                + "Regency=?,\n"
                + "UserName=?\n"
                + "where ManagerID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên

            preparedStatement.setString(1, updated.getPhoneNumber());
            preparedStatement.setString(2, updated.getName());
            preparedStatement.setString(3, updated.getEmail());
            preparedStatement.setBoolean(4, updated.isGender());
            preparedStatement.setString(5, updated.getDateOfBirth().toString());
            preparedStatement.setString(6, updated.getRegency().toString());
            preparedStatement.setString(7, updated.getName());
            preparedStatement.setInt(8, updated.getManagerID());
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    public boolean checkEmailDomManager(String email) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        String sql = "select count(*) from DomManager where Email = ?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return count > 0;
    }

    public boolean checkPhoneDomManager(String phone) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        String sql = "select count(*) from DomManager where PhoneNumber = ?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return count > 0;
    }

    public ArrayList<DomManager> getNotAuthorizedStaff() throws SQLException {
        ArrayList<DomManager> notAuthorized = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM DomManager where Authorize = 0";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            AccountDAO accountDAO = new AccountDAO();
            while (resultSet.next()) {
                DomManager domManager = new DomManager(resultSet.getInt("ManagerID"), // tạo mợi object của mình và bắt add vào list
                        resultSet.getString("ManagerName"),
                        resultSet.getBoolean("Gender"),
                        resultSet.getDate("DOB"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber"),
                        ManagerRegency.valueOf(resultSet.getString("Regency")),
                        (Account) accountDAO.getOne(resultSet.getString("UserName")));
                notAuthorized.add(domManager); // add vào list
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return notAuthorized;
    }

    public void makeAuthorize(String username) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Update DomManager set \n"
                + "Authorize=1 \n"
                + "where UserName = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên

            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }
}
