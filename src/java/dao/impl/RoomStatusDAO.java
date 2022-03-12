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
import model.BedStatus;
import model.Dom;
import model.Room;
import model.RoomStatus;

/**
 *
 * @author lenovo_thinkpad
 */
public class RoomStatusDAO extends Connection implements IBaseDAO {

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<RoomStatus> getDomStatus(Dom dom) throws SQLException {
        ArrayList<RoomStatus> domStatus = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT rs.RoomID,r.[Floor], SUM(Convert(int,[Status])) as 'FillStatus'\n"
                + "FROM RoomStatus rs, Room r\n"
                + "Where rs.RoomID=r.RoomID and r.domID = ?\n"
                + "group by rs.RoomID,r.[Floor]\n"
                + "order by r.[Floor] desc, rs.RoomID asc";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, dom.getDomID());
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            RoomDAO roomDAO = new RoomDAO();
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                Room r = ((Room) roomDAO.getOne(resultSet.getInt(1)));
                domStatus.add(new RoomStatus(r, (r.getCategory().getBedNumber() - resultSet.getInt(3)) < 0 ? 0 : (r.getCategory().getBedNumber() - resultSet.getInt(3)),
                        r.getCategory().getBedNumber() == 0 ? 7 : (int) (resultSet.getInt(3) * 7.0 / r.getCategory().getBedNumber())));// tạo mợi object của mình và bắt add vào list
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return domStatus;
    }

    @Override
    public Object getOne(Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object object, Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<BedStatus> getBedStatus(Room room) throws SQLException {
        ArrayList<BedStatus> bedStatuses = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from RoomStatus where RoomID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, room.getRoomID());
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                BedStatus bedStatus = new BedStatus(room, resultSet.getInt("Bedno"), resultSet.getBoolean("Status"));
                bedStatuses.add(bedStatus);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return bedStatuses;
    }

    public void setStatusNewBoarder(Room room, int bedNo) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "Update RoomStatus set [Status] = 1 Where RoomID = ? and BedNo = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, room.getRoomID());
            preparedStatement.setInt(2, bedNo);
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }
}
