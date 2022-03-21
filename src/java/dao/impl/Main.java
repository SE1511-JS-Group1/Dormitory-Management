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

import java.sql.Array;
import java.sql.SQLException;
import java.sql.Time;
import java.util.*;
import model.*;

/**
 *
 * @author lenovo_thinkpad
 */
public class Main {
    
    public static void main(String[] args) throws SQLException{
        FeedbackDAO dao = new FeedbackDAO();
        BoarderDAO boarder = new BoarderDAO();
        Boarder boar = boarder.getBoarderById(1);
        for(int i=0;i<20;i++){
            dao.insert(new Feedback(0, new java.sql.Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), "send feedback ddeer test feeback", boar));
        }
        

    }
}
