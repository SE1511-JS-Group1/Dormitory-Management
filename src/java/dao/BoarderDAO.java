/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
public class BoarderDAO implements IBaseService {

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> boarders = new ArrayList<>();
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "SELECT * FROM BoarderDAO";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            AccountDAO accountDB = new AccountDAO();
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (Result.next()) {
                Boarder boarder = new Boarder(Result.getString(1), // tạo mợi object của mình và bắt add vào list
                        Result.getString(2),
                        Result.getDate(3),
                        Result.getBoolean(4),
                        Result.getString(5),
                        Result.getString(6),
                        Result.getString(7).equalsIgnoreCase("Student") ? Jobs.Student : Jobs.Teacher,
                        (Account) accountDB.getOne(Result.getString(8)));
                boarders.add(boarder); // add vào list
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return boarders;
    }

    @Override
    public Object getOne(Object key) {
        try {
            for (Object o : getAll()) {
                if (((Boarder) o).getAccount().getUserName().equals((String) key)) {
                    return o;
                }
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException("Wrong Object and key to use this method!");
        }
        return null;
    }

    @Override
    public void insert(Object object) {
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        String sql = "INSERT INTO BOARDER(BoarderId,BoarderName,DOB,Gender,PhoneNumber,Job,Email,UserName) VALUES(?,?,?,?,?,?,?,?)";
        try {

            Boarder boarder = (Boarder) object;
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setString(1, boarder.getRollNumber());
            Statement.setString(2, boarder.getBoarderName());
            Statement.setDate(3, boarder.getDateOfBirth());
            Statement.setBoolean(4, boarder.isGender());
            Statement.setString(5, boarder.getPhoneNumber());
            Statement.setString(6, boarder.getJob().toString());
            Statement.setString(7, boarder.getEmail());
            Statement.setString(8, boarder.getAccount().getUserName());
            Statement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Wrong Object to use this method!");
        } finally {
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
    }

    @Override
    public void delete(Object object) {
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        String sql = "DELETE BOARDER WHERE BOARDERID=?";
        try {
            Boarder boarder = (Boarder) object;
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setString(1, boarder.getRollNumber());
            Statement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Wrong Object to use this method!");
        } finally {
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
    }

    @Override
    public void update(Object object, Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
