/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import model.Account;
import model.Boarder;
import model.BoardingInformation;
import model.Dom;
import model.Room;
import model.Jobs;

/**
 *
 * @author lenovo_thinkpad
 */
public class Main {

    public static void main(String[] args) throws ParseException {
        BoardingInformationDAO dao = new BoardingInformationDAO();
        ArrayList<Object> list = dao.getAll();
        BoardingInformation[] arr = new BoardingInformation[list.size()];
        list.toArray(arr);
        for(BoardingInformation b:arr) {
            System.out.println(b.getBedNo());
        }
//        System.out.println(b.getBoarderName());
//        Boarder[] arr = new Boarder[list.size()];
//        list.toArray(arr);
//        System.out.println(arr[0].getBoarderName());
//        String s = "2001-12-31";
//        Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(s);
//        System.out.println(date);
//        AccountDAO accDAO = new AccountDAO();
//        Account a = new Account("meimei", "hehe", 1);
//        accDAO.delete(a);
//        accDAO.update("hehe", "meimei");
//        Account a = new Account("meimei", "huhu", 1);
//        accDAO.insert(a);
//        Account a = (Account) accDAO.getOne("admin");
//        System.out.println(a.getUserName());
//        ArrayList<Object> list = accDAO.getAll();
//        
//        Account[] a = new Account[list.size()];
//        list.toArray(a);
//        for(int i=0; i<list.size(); i++) {
//            System.out.println(a[i].getUserName());
//        }
//        RoomDAO roomDAO = new RoomDAO();
//        RoomStatusDAO rsd = new RoomStatusDAO();
//        DomDAO dd = new DomDAO();
//        int count = 0;
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
