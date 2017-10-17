package collectiontest;


import collectiontest.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * DAO
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrateur
 */
public class StudentDAO implements DAOInterface <Student, StudentDAO> {
   private Connection dbConnection;
   private PreparedStatement pStatement;
   private ResultSet resultSet;

    public StudentDAO(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }
   
    /**
     * persistance de l entité Student
     * @param entity
     * @return 
     */
    
    @Override
    public int  save(Student entity) throws SQLException{
        int affectedRows;
        if(entity.getId() == null){
            //insertion 
            affectedRows = this.insert(entity);
        }else{
            //mise a jour
            affectedRows = this.update(entity);
        }
        return affectedRows;
        
    }
    /**
     * insertion dans la dase de donnéees
     * @param entity
     * @return 
     */
    //ajout
    private int insert(Student entity) throws SQLException{
        String sql = "INSERT INTO students (name, first_name, sex) VALUES (?,?, ?)";
         pStatement = dbConnection.prepareStatement(sql);
         pStatement.setString(1, entity.getName());
         pStatement.setString(2, entity.getFirstName());
         pStatement.setString(3, String.valueOf( entity.getSex()));
         
         return pStatement.executeUpdate();
    }
    
      /**
     * insertion dans la dase de donnéees
     * @param entity
     * @return 
     */
    //mise a jour
    
    private int update(Student entity) throws SQLException {
        String sql = "UPDATE  students SET name=?, first_name=?,sex=? WHERE id=?";
         pStatement = dbConnection.prepareStatement(sql);
         pStatement.setString(1, entity.getName());
         pStatement.setString(2, entity.getFirstName());
         pStatement.setString(3, String.valueOf( entity.getSex()));
         pStatement.setInt (4, entity.getId());
         return pStatement.executeUpdate();
    }
    /**
     * suppression d'une entité en fonction de son identifiant
     * @param entity 
     */
    @Override
    public void deleteOneById(Student entity) throws SQLException{
        if(entity.getId()!= null ){
            String sql = "DELETE FROM students WHERE id=?";
            pStatement = dbConnection.prepareStatement(sql);
            pStatement.setInt(1, entity.getId());
            pStatement.executeUpdate();
        }
    }

public StudentDAO findOneById(int id) throws SQLException{
    String sql="SELECT * FROM students WHERE id=?";
     pStatement = dbConnection.prepareStatement(sql);
            pStatement.setInt(1, id);
            pStatement.executeQuery();
    return this;
}
/**
 * 
 * @return
 * @throws SQLException 
 */

public Student getOne() throws SQLException{
    Student entity = new Student();
    if(resultSet.next()){
        entity.setName(resultSet.getString("name"));
        entity.setFirstName(resultSet.getString("first_name"));
        entity.setSex(resultSet.getString("sex").charAt(0));
        
    }
  
    return entity;
}

/**
 * execute une requete selectionnant l ensemble des lignes de la table
 * @return
 * @throws SQLException 
 */

public StudentDAO findAll() throws SQLException{
    String sql="SELECT * FROM students";
     pStatement = dbConnection.prepareStatement(sql);
           
            pStatement.executeQuery();
    return this;
}
/**
 * recuperation des resultat d une requete sous la forme
 * d un tableau associatif
 * @return
 * @throws SQLException 
 */

public Map<String, String> getOneAsMap() throws SQLException{
    Map<String, String> studentData = new HashMap<>();
    if(resultSet.next()){
        studentData.put("name", resultSet.getString("name"));
        studentData.put("firstName", resultSet.getString("firstName"));
        studentData.put("sex", resultSet.getString("sex"));
    }
    
    return studentData;
}


/**
 * recuperation des resultats d une requete sous la forme d une forme d unr liste d entité
 * @return 
 */
public List<Student> getAll() throws SQLException{
    List<Student> studentList = new ArrayList<>();
    while(! resultSet. isAfterLast()){
      studentList.add( this.getOne());
    }
    return studentList;
}
public List<Map<String, String>> getAllAsArray() throws SQLException{
   List<Map<String, String>> studentList = new ArrayList<>();
    while(! resultSet. isAfterLast()){
      studentList.add( this.getOneAsMap());
    }
    return studentList;
}


}





