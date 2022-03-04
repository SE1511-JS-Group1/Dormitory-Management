/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dell
 */
public class AccountDAOTest {
    
    public AccountDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAll method, of class AccountDAO.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        AccountDAO instance = new AccountDAO();
        ArrayList<Object> expResult = null;
        ArrayList<Object> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOne method, of class AccountDAO.
     */
    @Test
    public void testGetOne() throws Exception {
        System.out.println("getOne");
        Object key = null;
        AccountDAO instance = new AccountDAO();
        Object expResult = null;
        Object result = instance.getOne(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class AccountDAO.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        Object object = null;
        AccountDAO instance = new AccountDAO();
        instance.insert(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class AccountDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Object object = null;
        AccountDAO instance = new AccountDAO();
        instance.delete(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class AccountDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Object object = null;
        Object key = null;
        AccountDAO instance = new AccountDAO();
        instance.update(object, key);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
