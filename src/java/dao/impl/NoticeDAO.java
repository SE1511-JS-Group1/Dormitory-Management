/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * DAO Implement
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-27      1.3                 HuyLQ           Update code
 */
package dao.impl;

import dao.Connection;
import dao.IBaseDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Boarder;
import model.DomManager;
import model.Notice;

public class NoticeDAO extends Connection implements IBaseDAO {

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        ArrayList<Object> notices = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from Notices";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                int boarderID = resultSet.getInt(5); // lấy id của boarder từ database             
                BoarderDAO boarderDAO = new BoarderDAO();
                Boarder boarder = boarderDAO.getBoarderById(boarderID); //lấy object boarder thông qua phương thức getDomManagerById trong BoarderDAO

                int ManagerID = resultSet.getInt(6);  //lấy id của domManager từ database
                DomManagerDAO dmDAO = new DomManagerDAO();
                DomManager domManager = (DomManager) dmDAO.getDomManagerById(ManagerID); //lấy object DomManager thông qua phương thức getDomManagerById trong DomManagerDAO
                Notice notice = new Notice(resultSet.getInt("NoticeID"), //tạo mới một notice
                        resultSet.getTime("Time"),
                        resultSet.getDate("Time"),
                        resultSet.getString("Title"),
                        boarder,
                        domManager,
                        resultSet.getBoolean("Direction"));
                notices.add(notice);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return notices;
    }

    @Override
    public Object getOne(Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getNumberNoticesByBoarderId(int id) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select count(*) as 'total' from Notices where BoarderID=? ";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("total");
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

    public int getTotalPage(int id) throws SQLException {
        return getNumberNoticesByBoarderId(id) / 4 + (getNumberNoticesByBoarderId(id) % 4 == 0 ? 0 : 1);
    }

    public ArrayList<Notice> getNoticesByBoarderId(int id, int page) throws SQLException {
        ArrayList<Notice> notices = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from Notices where BoarderID=? Order by [Date] desc,[Time] desc OFFSET     ? ROWS "
                + "FETCH NEXT ? ROWS ONLY;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, (page - 1) * 4);
            preparedStatement.setInt(3, 4);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int boarderID = resultSet.getInt("BoarderID"); // lấy id của boarder từ database             
                BoarderDAO boarderDAO = new BoarderDAO();
                Boarder boarder = boarderDAO.getBoarderById(boarderID); //lấy object boarder thông qua phương thức getOne trong BoarderDAO

                int ManagerID = resultSet.getInt("ManagerID");  //lấy id của domManager từ database
                DomManagerDAO dmDAO = new DomManagerDAO();
                DomManager domManager = (DomManager) dmDAO.getOnee(ManagerID); //lấy object DomManager thông qua phương thức getOne trong DomManagerDAO
                Notice notice = new Notice(resultSet.getInt("NoticeID"), //tạo mới một notice
                        resultSet.getTime("Time"),
                        resultSet.getDate("Date"),
                        resultSet.getString("Title"),
                        boarder,
                        domManager,
                        resultSet.getBoolean("Direction"));
                notices.add(notice);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return notices;
    }

    @Override
    public void insert(Object object) throws SQLException {
        Notice inserted = (Notice) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "Insert into Notices (Direction, [Time], [Date], Title, BoarderID, ManagerID) values (?,?,?,?,?,?)";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setBoolean(1, inserted.isDirection());
            preparedStatement.setTime(2, inserted.getTimeSend());
            preparedStatement.setDate(3, inserted.getDateSend());
            preparedStatement.setString(4, inserted.getTitle());
            preparedStatement.setInt(5, inserted.getBoarder().getBoarderID());
            preparedStatement.setInt(6, inserted.getDomManager().getManagerID());
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
        Notice deleted = (Notice) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Delete Notices where NoticeID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, deleted.getId());
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void update(Object object, Object key) throws SQLException {
        Notice updated = (Notice) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Update Notices set Direction= ?, Time= ?, Title= ?, BoarderID= ?, ManagerID= ? "
                + "where NoticeID=?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setBoolean(1, updated.isDirection());
            preparedStatement.setString(2, updated.getTimeSend().toString());
            preparedStatement.setString(3, updated.getTitle());
            preparedStatement.setInt(4, updated.getBoarder().getBoarderID());
            preparedStatement.setInt(5, updated.getDomManager().getManagerID());
            preparedStatement.setInt(6, (int) key); //key là giá trị id của Notice cần thay đổi
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

}
