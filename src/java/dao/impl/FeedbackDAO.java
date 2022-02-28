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
import model.Boarder;
import model.Feedback;

/**
 *
 * @author lenovo_thinkpad
 */
public class FeedbackDAO extends Connection implements IBaseDAO {

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        ArrayList<Object> feedbacks = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Feedback";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            BoarderDAO boarderDAO = new BoarderDAO();
            while (resultSet.next()) {
                Feedback feedback = new Feedback(resultSet.getInt("[FeedbackID]"), // tạo mợi object của mình và bắt add vào list
                        resultSet.getDate("[TimeSend]"),
                        resultSet.getString("[Title]"),
                        (Boarder) boarderDAO.getOne(resultSet.getInt("[BoarderID]")));
                feedbacks.add(feedback); // add vào list
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return feedbacks;
    }

    @Override
    public Object getOne(Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Object object) throws SQLException {
        Feedback inserted = (Feedback) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO Feedback (TimeSend,BoarderID,Title)VALUES(?,?,?)";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setDate(1, inserted.getTimeSend());
            preparedStatement.setInt(2, inserted.getOwner().getBoarderID());
            preparedStatement.setString(3, inserted.getTitle());
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
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

}
