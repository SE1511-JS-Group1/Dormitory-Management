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

/**
 *
 * @author lenovo_thinkpad
 */
public class AccountDAO extends Connection implements IBaseDAO {

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        ArrayList<Object> accounts = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Account";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                Account account = new Account(resultSet.getString("Username"), // tạo mợi object của mình và bắt add vào list
                        resultSet.getString("Password"),
                        resultSet.getInt("RoleId"));
                accounts.add(account); // add vào list
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return accounts;
    }

    @Override
    public Object getOne(Object key) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Account WHERE userName = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, (String) key);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                Account account = new Account(resultSet.getString(1), // tạo mợi object của mình và bắt add vào list
                        resultSet.getString(2),
                        resultSet.getInt(3));
                return account; // add vào list
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
        Account inserted = (Account) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        String sql = "Insert into Account(UserName,PassWord,RoleId) values (?,?,?)";
        try {
            Connect = getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setString(1, inserted.getUserName());
            Statement.setString(2, inserted.getPassWord());
            Statement.setInt(3, inserted.getRole());
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
        Account deleted = (Account) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "Delete [Transaction] where WalletID = (select WalletID from [Wallet] where UserName = ?) "
                + "Delete [Wallet] where UserName = ? "
                + "Delete [Notices] where ManagerID = (select ManagerID from [DomManager] where UserName = ?) "
                + "Delete [Manager] where ManagerID = (select ManagerID from [DomManager] where UserName = ?) "
                + "Delete [Violation] where ManagerID = (select ManagerID from [DomManager] where UserName = ?)"
                + "Delete [DomManager] where UserName = ? "
                + "Delete [RoomFeeBill] where BoarderID = (select BoarderID from [Boarder] where UserName = ?) "
                + "Delete [Feedback] where BoarderID = (select BoarderID from [Boarder] where UserName = ?) "
                + "Delete [BoardingInformation] where BoarderID = (select BoarderID from [Boarder] where UserName = ?) "
                + "Delete [Notices] where BoarderID = (select BoarderID from [Boarder] where UserName = ?) "
                + "Delete [Boarder] where UserName = ? "
                + "Delete [Account] where UserName = ? ";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, deleted.getUserName());
            preparedStatement.setString(2, deleted.getUserName());
            preparedStatement.setString(3, deleted.getUserName());
            preparedStatement.setString(4, deleted.getUserName());
            preparedStatement.setString(5, deleted.getUserName());
            preparedStatement.setString(6, deleted.getUserName());
            preparedStatement.setString(7, deleted.getUserName());
            preparedStatement.setString(8, deleted.getUserName());
            preparedStatement.setString(9, deleted.getUserName());
            preparedStatement.setString(10, deleted.getUserName());
            preparedStatement.setString(11, deleted.getUserName());
            preparedStatement.setString(12, deleted.getUserName());
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void update(Object object, Object key) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "Update Account set PassWord = ? where UserName = ? ";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, (String) object);
            preparedStatement.setString(2, (String) key);
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

}
