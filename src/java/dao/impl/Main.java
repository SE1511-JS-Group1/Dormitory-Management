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

import java.sql.SQLException;
import java.util.*;
import model.*;

/**
 *
 * @author lenovo_thinkpad
 */
public class Main {
    
    public static void main(String[] args) throws SQLException{
        ViolationDAO dao = new ViolationDAO();
        Violation a = dao.getViolationByID(1);
        System.out.println(a.getDiscription());
    }
}
