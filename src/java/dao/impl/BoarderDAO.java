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
import model.Jobs;
import model.Boarder;
import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lenovo_thinkpad
 */
public class BoarderDAO extends Connection implements IBaseDAO {

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        ArrayList<Object> boarders = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Boarder";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            AccountDAO accountDB = new AccountDAO();
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                Boarder boarder = new Boarder(resultSet.getInt(1), // tạo mợi object của mình và bắt add vào list
                        resultSet.getString("BoarderName"),
                        resultSet.getDate("DOB"),
                        resultSet.getBoolean("Gender"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString(7).equalsIgnoreCase("Student") ? Jobs.Student : Jobs.Teacher,
                        (Account) accountDB.getOne(resultSet.getString(8)));
                boarders.add(boarder); // add vào list
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return boarders;
    }

    @Override
    public Object getOne(Object key) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Boarder WHERE UserName = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, (String) key);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            AccountDAO accountDB = new AccountDAO();
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
             while (resultSet.next()) {
                Boarder boarder = new Boarder(resultSet.getInt(1), // tạo mợi object của mình và bắt add vào list
                        resultSet.getString("BoarderName"),
                        resultSet.getDate("DOB"),
                        resultSet.getBoolean("Gender"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString(7).equalsIgnoreCase("Student") ? Jobs.Student : Jobs.Teacher,
                        (Account) accountDB.getOne((String) key));
                return  boarder;
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

    public Boarder getBoarderById(int id) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from Boarder where BoarderID = ?";

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int ID = resultSet.getInt(1); //lấy ID của boarder
                String name = resultSet.getString("BoarderName"); //lấy boarder name
                Date dob = resultSet.getDate("DOB"); //lấy boarder date of birth
                boolean gender = resultSet.getBoolean("Gender"); //lấy boarder gender
                String email = resultSet.getString("Email"); //lấy boarder gender
                String phonenumber = resultSet.getString("PhoneNumber"); //lấy boarder phonenumber
                Jobs job = Jobs.valueOf(resultSet.getString("Job")); //lấy boarder job ép kiểu về enum :3
                AccountDAO accDAO = new AccountDAO();
                Account a = (Account) accDAO.getOne(resultSet.getString("UserName")); //gọi phương thức getone lấy account của boarder bằng username
                Boarder boarder = new Boarder(ID, name, dob, gender, email, phonenumber, job, a);
                return boarder;
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
    
    public Boarder getBoarderByUserName(String username) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from Boarder where UserName = ?";

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int ID = resultSet.getInt(1); //lấy ID của boarder
                String name = resultSet.getString("BoarderName"); //lấy boarder name
                Date dob = resultSet.getDate("DOB"); //lấy boarder date of birth
                boolean gender = resultSet.getBoolean("Gender"); //lấy boarder gender
                String email = resultSet.getString("Email"); //lấy boarder gender
                String phonenumber = resultSet.getString("PhoneNumber"); //lấy boarder phonenumber
                Jobs job = Jobs.valueOf(resultSet.getString("Job")); //lấy boarder job ép kiểu về enum :3
                AccountDAO accDAO = new AccountDAO();
                Account a = (Account) accDAO.getOne(resultSet.getString("UserName")); //gọi phương thức getone lấy account của boarder bằng username
                Boarder boarder = new Boarder(ID, name, dob, gender, email, phonenumber, job, a);
                return boarder;
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
    public boolean checkEmailBoarder(String email) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        String sql = "select count(*) from Boarder where Email = ?";
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

    public boolean checkPhoneBoarder(String phone) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        String sql = "select count(*) from Boarder where PhoneNumber = ?";
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

    @Override
    public void insert(Object object) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO BOARDER(BoarderName,DOB,Gender,PhoneNumber,Job,Email,UserName) VALUES(?,?,?,?,?,?,?)";
        try {

            Boarder boarder = (Boarder) object;
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
//            Statement.setInt(1, boarder.getBoarderID());
            preparedStatement.setString(1, boarder.getBoarderName());
            preparedStatement.setString(2, boarder.getDateOfBirth().toString());
            preparedStatement.setBoolean(3, boarder.isGender());
            preparedStatement.setString(4, boarder.getPhoneNumber());
            preparedStatement.setString(5, boarder.getJob().toString());
            preparedStatement.setString(6, boarder.getEmail());
            preparedStatement.setString(7, boarder.getAccount().getUserName());
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
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE BOARDER WHERE BOARDERID = ?";
        try {
            Boarder boarder = (Boarder) object;
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, boarder.getBoarderID());
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void update(Object object, Object key) throws SQLException{
        Boarder updated = (Boarder) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Update Boarder set PassWord = ?  where UserName = ? ";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            //Statement.setString(1, updated.getPassWord());
            preparedStatement.setString(2, (String) key);
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
