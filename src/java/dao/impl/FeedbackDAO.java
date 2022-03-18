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

import com.sun.corba.se.spi.oa.OADefault;
import dao.Connection;
import dao.IBaseDAO;
import java.sql.Date;
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
                Feedback feedback = new Feedback(resultSet.getInt("FeedbackID"), // tạo mợi object của mình và bắt add vào list
                        resultSet.getDate("dateSend"),
                        resultSet.getTime("timeSend"),
                        resultSet.getString("Title"),
                        boarderDAO.getBoarderById(resultSet.getInt("BoarderID")));
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

    public ArrayList<Feedback> getpagefeedback(int page) throws SQLException {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Feedback \n"
                + "order by FeedbackID\n"
                + "offset ? rows \n"
                + "fetch next ? rows only";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, (page - 1) * 7);
            preparedStatement.setInt(2, 7);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            BoarderDAO boarderDAO = new BoarderDAO();
            while (resultSet.next()) {
                Feedback feedback = new Feedback(resultSet.getInt("FeedbackID"), // tạo mợi object của mình và bắt add vào list
                        resultSet.getDate("dateSend"),
                        resultSet.getTime("timeSend"),
                        resultSet.getString("Title"),
                        boarderDAO.getBoarderById(resultSet.getInt("BoarderID")));
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

    public ArrayList<Feedback> getpagefeedback(int page, Date date1, Date date2) throws SQLException {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Feedback  WHERE DateSend BETWEEN ? AND ? "
                + "order by FeedbackID\n"
                + "offset ? rows \n"
                + "fetch next ? rows only";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setDate(1, date1);
            preparedStatement.setDate(2, date2);
            preparedStatement.setInt(3, (page - 1) * 7);
            preparedStatement.setInt(4, 7);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            BoarderDAO boarderDAO = new BoarderDAO();
            while (resultSet.next()) {
                Feedback feedback = new Feedback(resultSet.getInt("FeedbackID"), // tạo mợi object của mình và bắt add vào list
                        resultSet.getDate("dateSend"),
                        resultSet.getTime("timeSend"),
                        resultSet.getString("Title"),
                        boarderDAO.getBoarderById(resultSet.getInt("BoarderID")));
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

    public int getTotalPage() throws SQLException {
        int page = getAll().size();
        return page / 7 + (page % 7 == 0 ? 0 : 1);
    }

    public int getTotalPage(Date date1, Date date2) throws SQLException {
        int page = search(date1, date2).size();
        return page / 7 + (page % 7 == 0 ? 0 : 1);
    }

    @Override
    public Object getOne(Object key) throws SQLException {
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Feedback where FeedbackID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setObject(1, key);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            BoarderDAO boarderDAO = new BoarderDAO();
            while (resultSet.next()) {
                return new Feedback(resultSet.getInt("FeedbackID"), // tạo mợi object của mình và bắt add vào list
                        resultSet.getDate("dateSend"),
                        resultSet.getTime("timeSend"),
                        resultSet.getString("Title"),
                        boarderDAO.getBoarderById(resultSet.getInt("BoarderID")));

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

    public ArrayList<Feedback> search(Date date1, Date date2) throws SQLException {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Feedback  WHERE DateSend BETWEEN ? AND ?;";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setDate(1, date1);
            preparedStatement.setDate(2, date2);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            BoarderDAO boarderDAO = new BoarderDAO();
            while (resultSet.next()) {
                Feedback feedback = new Feedback(resultSet.getInt("FeedbackID"), // tạo mợi object của mình và bắt add vào list
                        resultSet.getDate("dateSend"),
                        resultSet.getTime("timeSend"),
                        resultSet.getString("Title"),
                        boarderDAO.getBoarderById(resultSet.getInt("BoarderID")));
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
    public void insert(Object object) throws SQLException {
        Feedback inserted = (Feedback) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO Feedback (DateSend,TimeSend,BoarderID,Title)VALUES(?,?,?,?)";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setDate(1, inserted.getDateSend());
            preparedStatement.setTime(2, inserted.getTimeSend());
            preparedStatement.setInt(3, inserted.getOwner().getBoarderID());
            preparedStatement.setString(4, inserted.getTitle());
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
//        Feedback delete = (Feedback) object;
//        java.sql.Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        String sql = "DELETE  Feedback where FeedbackID = ?";
//        try {
//            connection = getConnection(); // Open 1 connect với Database của mình
//            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
//            preparedStatement.setInt(1, (int)Object);
//            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            closePreparedStatement(preparedStatement);
//            closeConnection(connection);
//        }
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE  Feedback where FeedbackID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, (int) object);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void update(Object object, Object key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
