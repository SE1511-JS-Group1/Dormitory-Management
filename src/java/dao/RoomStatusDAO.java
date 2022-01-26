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
import java.util.HashMap;
import model.Dom;
import model.Room;
import model.RoomStatus;

/**
 *
 * @author lenovo_thinkpad
 */
public class RoomStatusDAO implements IBaseService {

    @Override
    public ArrayList<Object> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<RoomStatus> getDomStatus(Dom dom) {
        ArrayList<RoomStatus> domStatus = new ArrayList<>();
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "SELECT rs.RoomID,r.[Floor], SUM(Convert(int,[Status])) as 'FillStatus'\n"
                + "FROM RoomStatus rs, Room r\n"
                + "Where rs.RoomID=r.RoomID and r.domID = ?\n"
                + "group by rs.RoomID,r.[Floor]\n"
                + "order by r.[Floor] desc, rs.RoomID asc";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setString(1, dom.getDomID());
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            RoomDAO roomDAO = new RoomDAO();
            RoomCategoryDAO rcdao = new RoomCategoryDAO();
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (Result.next()) {
                Room r = ((Room) roomDAO.getOne(Result.getInt(1)));
                domStatus.add(new RoomStatus(r, r.getCategory().getBedNumber() - Result.getInt(3),
                        r.getCategory().getBedNumber() == 0 ? 7 : (int) (Result.getInt(3) * 7.0 / r.getCategory().getBedNumber())));// tạo mợi object của mình và bắt add vào list
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return domStatus;
    }

    @Override
    public Object getOne(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
