/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.util.Date;
import model.Jobs;
import model.Boarder;
import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String sql = "SELECT * FROM Boarder";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            AccountDAO accountDB = new AccountDAO();
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (Result.next()) {
                Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(Result.getString("DOB"));
                Boarder boarder = new Boarder(Result.getInt(1), // tạo mợi object của mình và bắt add vào list
                        Result.getString("BoarderName"),
                        date,
                        Result.getBoolean("Gender"),
                        Result.getString("Email"),
                        Result.getString("PhoneNumber"),
                        Result.getString(7).equalsIgnoreCase("Student") ? Jobs.Student : Jobs.Teacher,
                        (Account) accountDB.getOne(Result.getString(8)));
                boarders.add(boarder); // add vào list
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(BoarderDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public Boarder getBoarderById(int id) {
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "select * from Boarder where BoarderID = ?";
        
        try {          
            Connect = Connection.getConnection();
            Statement = Connect.prepareStatement(sql);
            Statement.setInt(1, id);
            Result = Statement.executeQuery();
            while(Result.next()) {
                int ID = Result.getInt(1); //lấy ID của boarder
                String name = Result.getString("BoarderName"); //lấy boarder name
                Date dob = Result.getDate("DOB"); //lấy boarder date of birth
                boolean gender = Result.getBoolean("Gender"); //lấy boarder gender
                String email = Result.getString("Email"); //lấy boarder gender
                String phonenumber = Result.getString("PhoneNumber"); //lấy boarder phonenumber
                Jobs job = Jobs.valueOf(Result.getString("Job")); //lấy boarder job ép kiểu về enum :3
                AccountDAO accDAO = new AccountDAO();
                Account a = (Account) accDAO.getOne(Result.getString("UserName")); //gọi phương thức getone lấy account của boarder bằng username
                Boarder boarder = new Boarder(ID, name, dob, gender, email, phonenumber, job, a);
                return boarder;
            }
            
        } catch(SQLException e) {
            throw new UnsupportedOperationException("Wrong Object to use this method!");
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return null;
    }
    
    @Override
    public void insert(Object object) {
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        String sql = "INSERT INTO BOARDER(BoarderName,DOB,Gender,PhoneNumber,Job,Email,UserName) VALUES(?,?,?,?,?,?,?)";
        try {

            Boarder boarder = (Boarder) object;
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
//            Statement.setInt(1, boarder.getBoarderID());
            Statement.setString(1, boarder.getBoarderName());
            Statement.setString(2, boarder.getDateOfBirth().toString());
            Statement.setBoolean(3, boarder.isGender());
            Statement.setString(4, boarder.getPhoneNumber());
            Statement.setString(5, boarder.getJob().toString());
            Statement.setString(6, boarder.getEmail());
            Statement.setString(7, boarder.getAccount().getUserName());
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
        String sql = "DELETE BOARDER WHERE BOARDERID = ?";
        try {
            Boarder boarder = (Boarder) object;
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setInt(1, boarder.getBoarderID());
            Statement.executeUpdate(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Wrong Object to use this method!");
        } finally {
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
    }

    @Override
    public void update(Object object, Object key) {
        Boarder updated = (Boarder) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Update Boarder set PassWord = ?  where UserName = ? ";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            //Statement.setString(1, updated.getPassWord());
            Statement.setString(2, (String)key);
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
    }

}
