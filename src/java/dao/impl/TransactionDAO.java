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
import model.Transaction;
import model.Wallet;

/**
 *
 * @author Dell
 */
public class TransactionDAO extends Connection implements IBaseDAO {

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getOne(Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Object object) throws SQLException {
        Transaction transaction = (Transaction)object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "Insert Into [Transaction]([Type],Amount,[Message],[Date],[Time],WalletID) values (?,?,?,?,?,?)";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, transaction.getType());
            preparedStatement.setDouble(2, transaction.getAmount());
            preparedStatement.setString(3, transaction.getMessage());
            preparedStatement.setDate(4, transaction.getDate());
            preparedStatement.setTime(5, transaction.getTime());
            preparedStatement.setInt(6, transaction.getOwner().getWalletId());
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

    private int getTotalTransactions(int walletID) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Select Count(*) as 'Total' from [Transaction] where WalletID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, walletID);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            while (resultSet.next()) {
                return resultSet.getInt("Total");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return 0;
    }

    public int getNumberPage(int id) throws SQLException {
        return getTotalTransactions(id) / 3 + (getTotalTransactions(id) % 3 == 0 ? 0 : 1);
    }

    public ArrayList<Transaction> getTransactions(int walletID, int page) throws SQLException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Select * from [Transaction] where WalletID = ? Order by [Date] desc,[Time] desc OFFSET     ? ROWS "
                + "FETCH NEXT ? ROWS ONLY;";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, walletID);
            preparedStatement.setInt(2, (page - 1) * 3);
            preparedStatement.setInt(3, 3);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            while (resultSet.next()) {
                WalletDAO walletDAO = new WalletDAO();
                Wallet wallet = walletDAO.getWalletById(walletID);
                Transaction transaction = new Transaction(
                        resultSet.getInt("TransactionID"),
                        resultSet.getString("Type"), //tạo mới một notice
                        resultSet.getDouble("Amount"),
                        resultSet.getString("Message"),
                        resultSet.getDate("Date"),
                        resultSet.getTime("Time"),
                        wallet);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return transactions;
    }

    public void deleteWalletTransaction(Wallet wallet) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "Delete [Transaction] where WalletID = ? ";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, wallet.getWalletId());
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }
}
