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
import model.Dom;
import model.DomManager;
import model.Management;

/**
 *
 * @author lenovo_thinkpad
 */
public class ManagementDAO implements IBaseService{

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> managements = new ArrayList<>();
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "select * from Manager";
        System.out.println(Connection.getConnection());
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (Result.next()) {
                DomDAO domDAO = new DomDAO();
                Dom dom = (Dom) domDAO.getOne(Result.getString(1)); //gọi phương thức getOne lấy về 1 dom theo domID
                DomManagerDAO dmd = new DomManagerDAO();
                DomManager domManager = (DomManager) dmd.getOne(Result.getInt(2)); ////gọi phương thức getOne lấy về 1 domManager theo ID
                Management management = new Management(dom, domManager);                                                                    
                managements.add(management); // add vào list
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return managements;
    }

    @Override
    public Object getOne(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getManagement(Object domId, Object managerId) {
        ArrayList<Object> managements = new ArrayList<>();
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "select * from Manager where ? = ?";
        System.out.println(Connection.getConnection());
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            if(domId == null) { //tìm tất cả các dom mà managerId ở trên quản lý
                Statement.setString(1, "ManagerID"); //set dấu hỏi thứ nhất là 'managerID'
                Statement.setInt(2, (int)managerId); //set dấu hỏi thức 2 là giá trị của ID đó
                Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
                // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
                while (Result.next()) {
                    DomDAO domDAO = new DomDAO();
                    Dom dom = (Dom) domDAO.getOne(Result.getString(1)); //gọi phương thức getOne lấy về 1 dom theo domID
                    DomManagerDAO dmd = new DomManagerDAO();
                    DomManager domManager = (DomManager) dmd.getOne(Result.getInt(2)); ////gọi phương thức getOne lấy về 1 domManager theo ID
                    Management management = new Management(dom, domManager);                                                                    
                    managements.add(management); // add vào list
                }
                return managements;
            } else if(managerId == null) { //tìm tất cả các managerId trên quản lý domId trên
                Statement.setString(1, "DomID"); //set dấu hỏi thứ nhất là 'DomID'
                Statement.setString(2, (String)domId); //set dấu hỏi thức 2 là giá trị của ID đó
                Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
                while (Result.next()) {
                    DomDAO domDAO = new DomDAO();
                    Dom dom = (Dom) domDAO.getOne(Result.getString(1)); //gọi phương thức getOne lấy về 1 dom theo domID
                    DomManagerDAO dmd = new DomManagerDAO();
                    DomManager domManager = (DomManager) dmd.getOne(Result.getInt(2)); ////gọi phương thức getOne lấy về 1 domManager theo ID
                    Management management = new Management(dom, domManager);                                                                    
                    managements.add(management); // add vào list
                }
                return managements;
            } else {
                return null;
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return null;
    }
    @Override
    public void insert(Object object) {
        Management inserted = (Management) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Insert into Manager(ManagerID, DomID) values (?, ?)";
        System.out.println(Connection.getConnection());
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setInt(1, inserted.getDomManager().getManagerID());
            Statement.setString(2, inserted.getDom().getDomID());
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
    }

    @Override
    public void delete(Object object) {
        Management inserted = (Management) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Delete Manager where ManagerID = ? and DomID = ? ";
        System.out.println(Connection.getConnection());
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setInt(1, inserted.getDomManager().getManagerID());
            Statement.setString(2, inserted.getDom().getDomID());
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
    }

    @Override
    public void update(Object object, Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
