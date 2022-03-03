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
import model.DomInformation;

/**
 *
 * @author lenovo_thinkpad
 */
public class Main {
    
    public static void main(String[] args) throws SQLException{
        DomDAO aO = new DomDAO();
        aO.checkFloorFull("A", 1);
    }
}
