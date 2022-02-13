/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
public class DomManagerDAO implements IBaseService {

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> domManagers = new ArrayList<>();
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "SELECT * FROM DomManager";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            AccountDAO accountDAO = new AccountDAO();
            while (Result.next()) {
                DomManager domManager = new DomManager(Result.getInt("ManagerID"), // tạo mợi object của mình và bắt add vào list
                        Result.getString("ManagerName"),
                        Result.getBoolean("Gender"),
                        Result.getDate("DOB"),
                        Result.getString("Email"),
                        Result.getString("PhoneNumber"),
                        ManagerRegency.valueOf(Result.getString("Regency")),
                        (Account) accountDAO.getOne(Result.getString("UserName")));
                domManagers.add(domManager); // add vào list
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return domManagers;
    }

    @Override
    public Object getOne(Object key) {
        DomManager domManager = new DomManager();
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "SELECT * FROM DomManager where UserName = ? ";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setString(1, (String) key);
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            AccountDAO accountDAO = new AccountDAO();
            while (Result.next()) {
                domManager = new DomManager(Result.getInt("ManagerID"), // tạo mợi object của mình và bắt add vào list
                        Result.getString("ManagerName"),
                        Result.getBoolean("Gender"),
                        Result.getDate("DOB"),
                        Result.getString("Email"),
                        Result.getString("PhoneNumber"),
                        ManagerRegency.valueOf(Result.getString("Regency")),
                        (Account) accountDAO.getOne(Result.getString("UserName")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return domManager;
    }

    @Override
    public void insert(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object object, Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
