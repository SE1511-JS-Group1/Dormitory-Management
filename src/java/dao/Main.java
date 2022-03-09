/*
 * Copyright(C) 2022, FPT University.
 * Dormitory Management System:
 * DAO Implement
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2022-01-23      1.2                 DucHT           Update code
 */
package dao;

import dao.impl.BoarderDAO;
import dao.impl.DomManagerDAO;
import dao.impl.AccountDAO;
import dao.impl.ViolationDAO;
import java.sql.Array;
import java.sql.SQLException;
import java.util.*;
import model.Violation;

/**
 *
 * @author lenovo_thinkpad
 */
public class Main {
    
    public static void main(String[] args) throws SQLException{
       
        ViolationDAO vioDAO = new ViolationDAO();
        ArrayList<Object> list = vioDAO.getAll();
        Violation[] arr = new Violation[list.size()];
        list.toArray(arr);
        for(Violation p:arr) {
            System.out.println(p.getDiscription());
        }

    }
}
