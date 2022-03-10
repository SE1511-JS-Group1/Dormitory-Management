/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author lenovo_thinkpad
 */
public interface IBaseDAO {

    /**
     * Alow user get data about a <code>Object</code> from database. Result may
     * be an <code>ArrayList</code> of <code>Object</code>.
     *
     * @return an {@code  ArrayList<Object>} .
     */
    ArrayList<Object> getAll() throws Exception;

    /**
     * Alow user search an {@code Object} with its primary key in the database.
     * Result may be an {@code  ArrayList<Object>}
     * @return {@code  ArrayList<Object>}
     */
    Object getOne(Object key)throws Exception;

    void insert(Object object)throws Exception;

    void delete(Object object)throws Exception;

    void update(Object object, Object key)throws Exception;
}
