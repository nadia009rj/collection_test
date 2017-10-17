/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectiontest;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrateur
 */
public interface DAOInterface <T, DAO> {

    /**
     * suppression d'une entité en fonction de son identifiant
     * @param entity
     */
    void deleteOneById(T entity) throws SQLException;

    /**
     * persistance de l entité Student
     * @param entity
     * @return
     */
    int save(T entity) throws SQLException;
    
    DAO findOneById(int id)throws SQLException;
    
    T getOne() throws SQLException;
    
    DAO findAll() throws SQLException;
   
    Map<String, String> getOneAsMap() throws SQLException;
    
    List<T> getAll() throws SQLException;
    
    List<Map<String, String>> getAllAsArray() throws SQLException;
}
