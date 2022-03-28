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
import java.sql.Time;
import java.util.ArrayList;
import model.Account;
import model.Boarder;
import model.Transaction;
import model.Wallet;

/**
 *
 * @author Dell
 */
public class WalletDAO extends Connection implements IBaseDAO {

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getOne(Object key) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Wallet WHERE userName = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, (String) key);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                AccountDAO accountDAO = new AccountDAO();
                Account account = (Account) accountDAO.getOne(key);
                Wallet wallet = new Wallet(resultSet.getInt("WalletID"), // tạo mợi object của mình và bắt add vào list
                        account,
                        resultSet.getDouble("Balance"));
                return wallet;
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

    public Wallet getWalletById(int id) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Wallet WHERE walletId = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                AccountDAO accountDAO = new AccountDAO();
                Wallet wallet = new Wallet(resultSet.getInt("WalletID"), // tạo mợi object của mình và bắt add vào list
                        (Account) accountDAO.getOne(resultSet.getString("username")),
                        resultSet.getDouble("Balance"));
                return wallet;
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
        Boarder boarder = (Boarder) object;
        if (getOne(boarder.getAccount().getUserName()) != null) {
            return;
        }
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "Insert INTO Wallet(Balance,Username) values(?,?)";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setFloat(1, 0);
            preparedStatement.setString(2, boarder.getAccount().getUserName());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object object, Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteWalletOfAccount(Account account) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        TransactionDAO transactionDAO = new TransactionDAO();
        // Xoa lich su giao dich
        transactionDAO.deleteWalletTransaction((Wallet) getOne(account.getUserName()));
        String sql = "Delete Wallet where UserName = ? ";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, account.getUserName());
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    public void topup(Boarder b, double amount) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "Update Wallet set Balance=? where WalletId = ? ";
        try {
            Wallet wallet = (Wallet) getOne(b.getAccount().getUserName());
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setDouble(1, wallet.getBalance() + amount);
            preparedStatement.setInt(2, wallet.getWalletId());
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
            TransactionDAO tdao = new TransactionDAO();
            Transaction topup = new Transaction(0, "in", amount, "Top-up " + amount + " VND", new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), wallet);
            tdao.insert(topup);
        } catch (SQLException e) {
            throw e;
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    public void pay(Boarder b, double amount) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "Update Wallet set Balance=? where WalletId = ? ";
        try {
            Wallet wallet = (Wallet) getOne(b.getAccount().getUserName());
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setDouble(1, wallet.getBalance() - amount);
            preparedStatement.setInt(2, wallet.getWalletId());
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
            TransactionDAO tdao = new TransactionDAO();
            Transaction topup = new Transaction(0, "out", amount, "Paying fee " + amount + " VND", new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), wallet);
            tdao.insert(topup);
        } catch (SQLException e) {
            throw e;
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    public void cashout(Boarder b, double amount) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "Update Wallet set Balance=? where WalletId = ? ";
        try {
            Wallet wallet = (Wallet) getOne(b.getAccount().getUserName());
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setDouble(1, wallet.getBalance() - amount);
            preparedStatement.setInt(2, wallet.getWalletId());
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
            TransactionDAO tdao = new TransactionDAO();
            Transaction topup = new Transaction(0, "out", amount, "Cash out " + amount + " VND", new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), wallet);
            tdao.insert(topup);
        } catch (SQLException e) {
            throw e;
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }
}
