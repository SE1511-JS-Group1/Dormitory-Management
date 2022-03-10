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
import java.util.*;
import model.Boarder;
import model.BoardingInformation;
import model.Room;

/**
 *
 * @author ngoc duy
 *
 */
public class BoardingInformationDAO extends Connection implements IBaseDAO {

    
    public BoardingInformation getBoardingInformation(int BoarderID) throws SQLException {
        BoardingInformation boardingInformation = null;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM BoardingInformation WHERE BoarderID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, BoarderID);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            RoomDAO roomDAO = new RoomDAO();
            BoarderDAO boarderDAO = new BoarderDAO();
            while (resultSet.next()) {
                boardingInformation = new BoardingInformation((Room) roomDAO.getOne(resultSet.getInt("RoomID")), // tạo mợi object của mình và bắt add vào list
                        boarderDAO.getBoarderById(resultSet.getInt("BoarderID")),
                        resultSet.getInt("BedNo"),
                        resultSet.getDate("StartDate"),
                        resultSet.getDate("EndDate"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return boardingInformation;
    }

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        ArrayList<Object> boadingInformations = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM BoardingInformation";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            RoomDAO roomDAO = new RoomDAO();
            BoarderDAO boarderDAO = new BoarderDAO();
            while (resultSet.next()) {
                BoardingInformation boardingInformation = new BoardingInformation((Room) roomDAO.getOne(resultSet.getInt("RoomID")), // tạo mợi object của mình và bắt add vào list
                        (Boarder) boarderDAO.getBoarderById(resultSet.getInt("BoarderID")),
                        resultSet.getInt("BedNo"),
                        resultSet.getDate("StartDate"),
                        resultSet.getDate("EndDate"));
                boadingInformations.add(boardingInformation); // add vào list
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return boadingInformations;
    }

    @Override
    public Object getOne(Object key) throws SQLException {
        throw new UnsupportedOperationException("This method not created yet!");
    }

    @Override
    public void insert(Object object) throws SQLException {
        BoardingInformation boardingInformation = (BoardingInformation) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Insert into BoardingInformation(BoarderID,RoomID,StartDate,EndDate,BedNo) values (?,?,?,?,?)";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, boardingInformation.getBoarder().getBoarderID());
            preparedStatement.setInt(2, boardingInformation.getRoom().getRoomID());
            preparedStatement.setDate(3, boardingInformation.getStartDate());
            preparedStatement.setDate(4, boardingInformation.getEndDate());
            preparedStatement.setInt(5, boardingInformation.getBedNo());
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
        BoardingInformation boardingInformation = (BoardingInformation) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Insert into BoardingInformation(BoarderID,RoomID,StartDate,BedNo) values (?,?,?,?)";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, boardingInformation.getBoarder().getBoarderID());
            preparedStatement.setInt(2, boardingInformation.getRoom().getRoomID());
            preparedStatement.setDate(3, boardingInformation.getStartDate());
            preparedStatement.setInt(4, boardingInformation.getBedNo());
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
    public void update(Object object, Object key) throws SQLException {
        BoardingInformation updated = (BoardingInformation) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Update BoardingInformation set RoomID = ?, StartDate = ?, EndDate = ?, BedNo = ? "
                + "where BoarderID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setInt(1, updated.getRoom().getRoomID());
            preparedStatement.setString(2, updated.getStartDate().toString());
            preparedStatement.setString(3, updated.getEndDate().toString());
            preparedStatement.setInt(5, updated.getBedNo());
            preparedStatement.setInt(6, (int) key);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

}
