/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    private boolean operationStatus;
    private String operationMessage;
    
    DBConnection (){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.conn = (Connection) DriverManager.getConnection(this.url, this.dbUser, this.dbPass);
            this.operationStatus = true;
            this.operationMessage = "Connected to database.";
            System.out.println("Connected to database server");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
            System.out.println("A connection with the database could not be stablished.");
            this.operationStatus = false;
            this.operationMessage = "Could not connect to database.";
            e.printStackTrace(System.out);
        }
    }
    
    /**
     * Creates a concatenated string with an start, the fields separated by another string, and an end
     * @param array the array to be converted into a string
     * @param separator the delimiter for each element of the array in the string.
     * @param startsWith a String to be inserted at the start of the resulting string
     * @param endsWith a String to be inserted at the end of the resulting String
     * @return 
     */
    private String arrayListToString(ArrayList array, String separator, String startsWith, String endsWith){
        // crea el string a devolver
        String retString = startsWith;
        // rellena el string
        Iterator i = array.iterator();
        while(i.hasNext()){
            Object element = i.next();
            retString += element + separator;
        }
        // quita el separador del último elemento insertado
        retString = retString.substring(0, retString.length() - separator.length());
        // añade el final
        retString += endsWith;
        
        return retString;
    }
    /**
     * Creates a string from an array list with a separator between fields
     * @param array array to be converted to string
     * @param separator separator to be inserted between fields
     * @return 
     */
    private String arrayListToString(ArrayList array, String separator){
        return arrayListToString(array, separator, "", "");
    }
    /**
     * Creates a coma-separated string from an arraylist
     * @param array array containing the fields to be concatenated
     * @return 
     */
    private String arrayListToString(ArrayList array){
        return arrayListToString(array, ",");
    }
    
    
    /**
     * Creates a record in the database
     * @param table Table in which the record will be inserted
     * @param field An array of strings containing the fields to be affected by the insert operation
     * @param value An array of the values in correlated order with the fields to be inserted
     * @return true if the operation was successful, false if not
     */
    public void insert(String table, ArrayList field, ArrayList value){
        // crea la query (string) para la inserción
        String query;
        query = "INSERT INTO " + table + " (" + arrayListToString(field) + ")";
        query += " VALUES (" + arrayListToString(value, "','", "'", "'") + ")";
        
        // realiza el insert
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.execute();
            
            this.operationStatus = true;
            this.operationMessage = "Insert sucessful";
            
        } catch (SQLException e){
            this.operationStatus = false;
            this.operationMessage = e.getMessage();
            e.printStackTrace(System.out);
        }
    }
    
    /**
     * Updates a record in the database
     * @param table Table in which the record will be updated
     * @param lookup Field to lookup for the change operation
     * @param lookupValue Value of the field to lookup for the change operation
     * @param field Contains a list of the fields to be updated
     * @param value Contains the new values in correlated order to the fields
     * @return 
     */
    public boolean update(String table, String lookup, String lookupValue, ArrayList field, ArrayList value){
        // crea un nuevo arrayList con la forma "field = value"
        ArrayList fieldsAndValues = new ArrayList();
        // realiza la combinación
        Iterator i = field.iterator();
        Iterator j = value.iterator();
        while(i.hasNext()){
            Object fieldString = i.next();
            Object valueString = j.next();
            fieldsAndValues.add(fieldString + " = " + valueString);
        }
        // crea la query
        String query;
        query = "UPDATE " + table + " SET " + arrayListToString(fieldsAndValues);
        query += " WHERE " + lookup + " = " + lookupValue;
        
        // realiza la operación
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.execute();
            
        } catch (SQLException e){
            this.operationStatus = false;
            this.operationMessage = e.getMessage();
            e.printStackTrace(System.out);
            return false;
        }
        
        this.operationStatus = true;
        this.operationMessage = "Update sucessful";
        return true;
    }
    
    /**
     * Deletes a record from the database
     * @param table Table to which the delete operation will afect
     * @param field Field by which the record will be identified
     * @param value Value of the field of the removed object
     * @return true if the operation was successful, false if not
     */
    public boolean delete(String table, String field, String value){
        // crea la query
        String query;
        query = "DELETE FROM " + table + " WHERE " + field + " = " + value;
        
        // realiza la operación
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.execute();
            
        } catch (SQLException e){
            this.operationStatus = false;
            this.operationMessage = e.getMessage();
            e.printStackTrace(System.out);
            return false;
        }
        
        return true;
    }
    
    /**
     * Devuelve un registro de la base de datos
     * @param table Table to which the retrieve operation will afect
     * @param lookup 
     * @param lookupValue
     * @return
     * @throws SQLException 
     */
    public ResultSet retrieve (String table, String lookup, String lookupValue) throws SQLException{
        String query = "SELECT * FROM " + table + " WHERE " + lookup + " = '" + lookupValue + "'";
        System.out.println(query);
        PreparedStatement st = conn.prepareStatement(query);
        return st.executeQuery();
    }
    
    public void close() throws SQLException{
        this.conn.close();
    }

    public boolean isOperationStatus() {
        return operationStatus;
    }

    public String getOperationMessage() {
        return operationMessage;
    }
    
    
}
