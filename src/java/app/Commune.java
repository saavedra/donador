/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alexis Saavedra
 */
public class Commune {

    private int id;
    private String name;
    private Region region;
    
    Commune(){}
    
    Commune(int id) throws SQLException{
        this.id = id;
        // consulta nombre e id de región a la base de datos
        DBConnection db = new DBConnection();
        ResultSet commune = db.retrieve("commune", "id_commune", "" + id);
        
        while (commune.next()){
            name = commune.getString("name");
            this.region = new Region(commune.getInt("region_commune"));
        }
    }
    
    Commune(int id, String name, Region region){
        this.id = id;
        this.name = name;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Region getRegion() {
        return region;
    }

	

}
