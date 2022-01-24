/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.HashMap;
import model.Dom;
import model.Room;

/**
 *
 * @author lenovo_thinkpad
 */
public class Main {

    public static void main(String[] args) {
        RoomDAO roomDAO = new RoomDAO();
        RoomStatusDAO rsd = new RoomStatusDAO();
        DomDAO dd = new DomDAO();
        int count = 0;
//        HashMap<Integer, Integer> roomstatus = roomDAO.getStatus();
//        for (int a : roomstatus.keySet()) {
////            System.out.println(a+"---"+roomstatus.get(a));
//            for (int i = 1; i <= roomstatus.get(a); i++) {
//                if (count % 1000 == 0) {
//                    System.out.println("\nINSERT INTO RoomStatus([RoomID],[BedNo],[Status]) values ");
//                }
//                count++;
//                if (count % 1000 == 999) {
//                    System.out.print("(" + a + "," + i + "," + (int) (Math.random() * 2) + ")");
//                } else {
//                    System.out.print("(" + a + "," + i + "," + (int) (Math.random() * 2) + "),");
//                }
//                if (count % 8 == 0) {
//                    System.out.println("");
//                }
//            }
//        }
//        Dom a = (Dom) dd.getOne("A");
//        HashMap<Room, Integer> domStatus = rsd.getDomStatus(a);
//        System.out.println(rsd.getDomStatus(a).size());
//        for (Room r : domStatus.keySet()) {
//            System.out.println(r+"--"+domStatus.get(r));
//        }
            }
}
