/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectiontest;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrateur
 */
public class CollectionTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //List c est interface course c est la variable
        List<String> course = new ArrayList<String>();
        
        //ajout d elements au tableau
        course.add("du lait");
        course.add("des oeufs");
        course.add("une conscience");
        
        //affichage du 2 eme element
        
        System.out.println(course.get(1));
        
        int nbElement = course.size();
        for(int i =0; i< nbElement; i++){
            System.out.println("course.get(i)");
        }
        //boucle ameliore
        
        for(String element:course){
            System.out.println(element);
            
        }
        //boucle avec un iterateur has next c est un bouleen 
        for (Iterator<String> iterator = course.iterator(); iterator.hasNext();) {
            String next = iterator.next();
            System.out.println(next);
            
        }
        //set i ln y a pas de doublon 
        System.out.println("Gestion des set");
        Set<String> languages = new TreeSet<>();
        
        languages.add("Java");
        languages.add("C#");
        languages.add("Ada");
        languages. add("Java");
        //pour chaque language dans le list languages
        
        for (String language : languages){
            System.out.println(language);
        }
        
        //le map on utilise put au lieu de add
      
        System.out.println("Gestion des map");
        Map<String, String> person = new HashMap<>();
        
        person.put("Nom", "Tossem");
        person.put("prenom", "Fabiola");
        person.put("age", "9");
        
        //affichage d une cle
        System.out.println(person.get("Nom"));
        
        Set keyList = person.keySet();
        
        for (Object key : keyList) {
            System.out.println(key.toString());
            System.out.println(person.get(key.toString()));
            
        }
        
        Collection<String>personInfo = person.values();
        for (String item : personInfo){
        System.out.println(item);
        }
        System.out.println("list de Map");
        List<Map<String,String>> students = new ArrayList<>();
        
        Map<String,String>student = new HashMap<>();
        
        student.put("name", "Manantsoa");
        student.put("firstName", "Lucas");
        student.put("sex", "M");
        students.add(student);
        
        student = new HashMap<>();
        student.put("name", "Irintsoa");
        student.put("firstName", "Fabiola");
        student.put("sex", "F");
        students.add(student);
        
        student = new HashMap<>();
        student.put("name", "Tossem");
        student.put("firstName", "Dylane");
        student.put("sex", "M");
        students.add(student);
      
       // System.out.println("student");
        //for (int i = 0; i < students.size(); i++) {
          //  System.out.println( "bonjour "+ students.get(i).get("name"));
           Map<String,String> item;
           for(int i = 0; i<students.size(); i++){
               item = students.get(i);
               StringBuilder sb = new StringBuilder();
               sb.append("bonjour ");
               
               if(item.get("sex").equals("F")){
               sb.append("madame ");
               
               
           }else {
                   sb.append("monsieur ");
               }
               sb.append(item.get("firstName"));
               sb.append(" ");
               sb.append(item.get("name"));
               System.out.println(sb.toString());
            
        }      
        String civilite;
        for(Map<String, String> element: students){
            if(element.get("sex") == "F"){
                civilite = "madame";
            }else{
                civilite="monsieur";
            }
            
            System.out.println("Bonjour " 
                               + civilite + " "
                              + element.get("firstName")
                              + " "
                                      + " " + element.get("name"));
        }
        //liste des etudiants avec une approche plus orientee objet(poo)
        List<Student> studentList = new ArrayList<>();
        
        //remplissage de la liste
        studentList.add(new Student ("Lucas", "Manantsoa",  'M'));
        studentList.add(new Student ("Fabiola", "Irintsoa",  'F'));
         studentList.add(new Student ("Dylane", "Tossem",  'M'));
         
         //affichage de la liste
         for(Student st: studentList){
             st.greet();
         }
        try {
            // test de la persistance avec le composant DAO
            Connection cn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/bibliotheque",
                    "root",
                    "");
            //instanciation duDTO
            Student tycho = new Student("Brahe", "Tycho", 'M');
            //persistance du DTO
            //INSTANCIATION  DU DAO
            StudentDAO dao = new StudentDAO(cn);
            dao.save(tycho);
        } catch (SQLException ex) {
            Logger.getLogger(CollectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
}
