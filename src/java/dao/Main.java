/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author lenovo_thinkpad
 */
public class Main {

    public static void main(String[] args) {
        BoarderDAO boarderDAO = new BoarderDAO();
        System.out.println(boarderDAO.getAll().size());
    }
}
