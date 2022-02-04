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
import model.Boarder;
import model.BoardingInformation;
import model.Room;

/**
 *
 * @author lenovo_thinkpad
 */
public class BoardingInformationDAO implements IBaseService {

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> boadingInformations = new ArrayList<>();
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "SELECT * FROM BoardingInformation";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            RoomDAO roomDAO = new RoomDAO();
            BoarderDAO boarderDAO = new BoarderDAO();
            while (Result.next()) {
                BoardingInformation boardingInformation = new BoardingInformation((Room) roomDAO.getOne(Result.getInt("RoomID")), // tạo mợi object của mình và bắt add vào list
                        (Boarder) boarderDAO.getOne(Result.getInt("BoarderID")),
                        Result.getInt("BedNo"),
                        Result.getDate("StartDate"),
                        Result.getDate("EndDate"));
                boadingInformations.add(boardingInformation); // add vào list
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
        return boadingInformations;
    }

    @Override
    public Object getOne(Object key) {
        throw new UnsupportedOperationException("This method not created yet!");
    }

    @Override
    public void insert(Object object) {
        BoardingInformation boardingInformation = (BoardingInformation) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Insert into BoardingInformation(BoarderID,RoomID,StartDate,BedNo) values (?,?,?,?)";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setInt(1, boardingInformation.getBoarder().getBoarderID());
            Statement.setInt(2, boardingInformation.getRoom().getRoomID());
            Statement.setDate(3, boardingInformation.getStartDate());
            Statement.setInt(4, boardingInformation.getBedNo());
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
        BoardingInformation boardingInformation = (BoardingInformation) object;
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
        String sql = "Insert into BoardingInformation(BoarderID,RoomID,StartDate,BedNo) values (?,?,?,?)";
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Statement.setInt(1, boardingInformation.getBoarder().getBoarderID());
            Statement.setInt(2, boardingInformation.getRoom().getRoomID());
            Statement.setDate(3, boardingInformation.getStartDate());
            Statement.setInt(4, boardingInformation.getBedNo());
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
