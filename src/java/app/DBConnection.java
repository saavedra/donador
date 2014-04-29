/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Alexis Saavedra
 */
public class DBConnection {
    private final String dbUser = "root";
    private final String dbPass = "M5cOeOQ8";
    
    private final String dbHost = "127.0.0.1";
    private final String dbPort = "3306";
    
    private final String dbName = "donador";
    private final String url = "jdbc:mysql://" + this.dbHost + ":" + this.dbPort + "/" + this.dbName;
    private Connection conn;
    
    DBConnection (){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.conn = (Connection) DriverManager.getConnection(this.url, this.dbUser, this.dbPass);
            System.out.println("Connected to database server");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
            System.out.println("A connection with the database could not be stablished.");
            e.printStackTrace(System.out);
        }
    }
    
    public boolean insert(String table, ArrayList field, ArrayList value){
        // crea los strings de la consulta
        String fieldsEnumeration = "";
        String valuesEnumeration = "";
        // define el string de los campos
        Iterator i = field.iterator();
        while(i.hasNext()){
            Object element = i.next();
            fieldsEnumeration += element + ",";
        }
        // define el string de los valores
        i = value.iterator();
        while(i.hasNext()){
            Object element = i.next();
            element = "'" + element + "'";
            valuesEnumeration += element + ",";
        }
        
        //quita las comas (,) al final del primer string y coma y apóstrofe del segundo (,')
        fieldsEnumeration = fieldsEnumeration.substring(0, fieldsEnumeration.length()-1);
        valuesEnumeration = valuesEnumeration.substring(0, valuesEnumeration.length()-1);
        
        // crea la query (string) para la inserción
        String query;
        query = "INSERT INTO " + table + " (" + fieldsEnumeration + ")";
        query += " VALUES (" + valuesEnumeration + ")";
        
        // realiza el insert
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.execute();
            
        } catch (SQLException e){
            e.printStackTrace(System.out);
            return false;
        }
        
        return true;
    }
    
    public void close() throws SQLException{
        this.conn.close();
    }
}
