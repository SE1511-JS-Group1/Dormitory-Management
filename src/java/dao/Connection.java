/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lenovo_thinkpad
 */
public class Connection implements ISQLContext{

    public java.sql.Connection getConnection() {
        java.sql.Connection connection = null; // create connection
        String connectionUrl = "jdbc:sqlserver://" + HOSTNAME + ":" + PORT + ";"
                + "databaseName=" + DATABASENAME + ";integratedSecurity=" + INTEGRATEDSECURITY + ";";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // đăng kí một cái driver
        } catch (ClassNotFoundException e) {
            System.err.println("Where is your MSSQL JDBC Driver?");
            return connection;
        }
//        System.out.println("MSSQL JDBC Driver Registered!");
        try {
            connection = DriverManager.getConnection(connectionUrl, ISQLContext.USERNAME, ISQLContext.PASSWORD); // mở một kết nối đến driver
        } catch (SQLException ex) {
            System.err.println("Connection Failed! Check output console");
            return connection;
        }
        return connection;
    }

    public void closeConnection(java.sql.Connection con) { // đóng kết nối
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }

    /**
     * Close PrepareStatement to MSSQL Sever
     *
     * @param ps
     */
    public void closePreparedStatement(PreparedStatement ps) { // đóng biên dịch sql
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                System.err.println("Close PreparedStatement Fail!");
            }
        }
    }

    /**
     * Close ResultSet to MSSQL Sever
     *
     * @param rs
     */
    public void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Close PreparedStatement Fail!");
            }
        }
    }
}
