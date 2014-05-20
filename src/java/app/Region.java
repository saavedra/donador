/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Alexis Saavedra
 */
public class Region {
    private int number;
    private String name;

    Region(){
    }
    
    Region(int number){
        // carga la región desde la base de datos, de acuerdo con el nombre especificado
        if(number == 9)
            this.name = "La Araucanía";
        else
            this.name = "Metropolitana";
    }

    public int getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }
    
    public String listCommunes() throws SQLException{
        ArrayList listOfCommunes = null;
        
        DBConnection db = new DBConnection();
        ResultSet communes = db.retrieve("commune", "region_commune" , "9");
        while (communes.next()){
            String name = communes.getString("name");
            int id = communes.getInt("id_commune");
            
            listOfCommunes.add(" { \"id\" : \"" + id + "\", \"name\" : \"" + name + " \" },");
        }
        
        String retString = "{ \"commune\" : [ ";
        Iterator i = listOfCommunes.iterator();
        while(i.hasNext()){
            Object element = i.next();
            retString += element;
        }
        // quita la última coma 
        retString = retString.substring(0, retString.length() - 1);
        // cierra el json
        retString += " ] }";
        
        return retString;
    }

}