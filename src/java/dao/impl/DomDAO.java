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
import model.Dom;
import model.DomInformation;

/**
 *
 * @author lenovo_thinkpad
 */
public class DomDAO extends Connection implements IBaseDAO {

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        ArrayList<Object> doms = new ArrayList<>();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Dom";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                Dom dom = new Dom(resultSet.getString(1), // tạo mợi object của mình và bắt add vào list
                        resultSet.getString(2));
                doms.add(dom); // add vào list
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return doms;
    }

    @Override
    public Object getOne(Object key) throws SQLException {
        Object dom = new Dom();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Dom Where DomID =?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, (String) key);
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                dom = new Dom(resultSet.getString(1), //get được object dom thông qua id của dom
                        resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return dom;
    }

    @Override
    public void insert(Object object) throws SQLException {
        Dom inserted = (Dom) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Insert into [Dom](DomID, DomName) values (?, ?)";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, inserted.getDomID());
            preparedStatement.setString(2, inserted.getDomName());
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
        Dom deleted = (Dom) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Delete Dom where DomID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, deleted.getDomID());
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
        Dom updated = (Dom) object;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Update Dom set DomName = ?  where DomID = ? ";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, updated.getDomName());
            preparedStatement.setString(2, updated.getDomID());
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }

    }

    private Dom getLastDom() throws SQLException {
        Dom dom = new Dom();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT TOP(1) * from Dom ORDER BY DomID desc";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                dom = new Dom(resultSet.getString(1), //get được object dom thông qua id của dom
                        resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return dom;
    }

    public void addNewDom() throws SQLException {
        Dom lastDom = getLastDom();
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "Insert into [Dom](DomID, DomName) values (?, ?) "
                + "Insert into Room(RoomName,Floor,DomID,CategoryID) values (?,?,?,?) ";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, String.valueOf((char) (lastDom.getDomID().charAt(0) + 1)));
            preparedStatement.setString(2, "Dom " + String.valueOf((char) (lastDom.getDomID().charAt(0) + 1)));
            preparedStatement.setString(3, "101");
            preparedStatement.setInt(4, 1);
            preparedStatement.setString(5, String.valueOf((char) (lastDom.getDomID().charAt(0) + 1)));
            preparedStatement.setInt(6, 0);
            preparedStatement.executeUpdate(); // Chạy và thực thi câu SQL
        } catch (SQLException e) {
            throw e;
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    public int getTotalBoarder(Dom dom) throws SQLException {
        int total = 0;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select d.DomID, sum(CONVERT(int,rs.[Status])) as 'TotalBoarder'\n"
                + "from Dom d, Room r, RoomStatus rs\n"
                + "where d.DomID=r.DomID and r.RoomID = rs.RoomID and d.DomID = ?\n"
                + "group by d.DomID";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, dom.getDomID());
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                total = resultSet.getInt(2);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return total;
    }

    public int getTotalRoom(Dom dom) throws SQLException {
        int total = 0;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT COUNT(*) FROM Room where DomID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, dom.getDomID());
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                total = (int) resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return total;
    }

    public int getTotalBed(Dom dom) throws SQLException {
        int total = 0;
        java.sql.Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT COUNT(*)\n"
                + "FROM RoomStatus rs,Room r\n"
                + "WHERE r.RoomID=rs.RoomID AND r.DomID = ?";
        try {
            connection = getConnection(); // Open 1 connect với Database của mình
            preparedStatement = connection.prepareStatement(sql); // Biên dịch câu SQL ở trên
            preparedStatement.setString(1, dom.getDomID());
            resultSet = preparedStatement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (resultSet.next()) {
                total = (int) resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return total;
    }

    public ArrayList<DomInformation> getDomInformations() throws SQLException {
        ArrayList<DomInformation> domInformations = new ArrayList<>();
        ManagementDAO managementDAO = new ManagementDAO();
        for (Object dom : getAll()) {
            domInformations.add(new DomInformation(
                    (Dom) dom, managementDAO.getDomManagers((Dom) dom),
                    getTotalRoom((Dom) dom), getTotalBed((Dom) dom),
                    getTotalBoarder((Dom) dom)));
        }
        return domInformations;
    }
}
