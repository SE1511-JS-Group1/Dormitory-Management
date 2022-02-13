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

/**
 *
 * @author lenovo_thinkpad
 */
public class DomDAO implements IBaseService {

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> doms = new ArrayList<>();
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "SELECT * FROM Dom";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (Result.next()) {
                Dom dom = new Dom(Result.getString(1), // tạo mợi object của mình và bắt add vào list
                        Result.getString(2));
                doms.add(dom); // add vào list
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return doms;
    }

    @Override
    public Object getOne(Object key) {
        Object dom = new Dom();
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "SELECT * FROM Dom Where DomID =?";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setString(1, (String) key);
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (Result.next()) {
                dom = new Dom(Result.getString(1), //get được object dom thông qua id của dom
                        Result.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return dom;
    }

    @Override
    public void insert(Object object) {
        Dom inserted = (Dom) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Insert into [Dom](DomID, DomName) values (?, ?)";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setString(1, inserted.getDomID());
            Statement.setString(2, inserted.getDomName());           
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
        Dom deleted = (Dom) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Delete Dom where DomID = ?";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setString(1, deleted.getDomID());
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
        Dom updated = (Dom) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Update Dom set DomName = ?  where DomID = ? ";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setString(1, updated.getDomName());
            Statement.setString(2, updated.getDomID());
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        
    }

    public int getTotalBoarder(Dom dom) {
        int total = 0;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "select d.DomID, sum(CONVERT(int,rs.[Status])) as 'TotalBoarder'\n"
                + "from Dom d, Room r, RoomStatus rs\n"
                + "where d.DomID=r.DomID and r.RoomID = rs.RoomID and d.DomID = ?\n"
                + "group by d.DomID";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setString(1, dom.getDomID());
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (Result.next()) {
                total = Result.getInt(2);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return total;
    }
}
