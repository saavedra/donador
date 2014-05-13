/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

/**
 *
 * @author Alexis Saavedra
 */
public class Commune {

    private int id;
    private String name;
    private Region region;
    
    Commune(){}
    
    Commune(int id){
        this.id = id;
        // consulta nombre e id de región a la base de datos
        this.name = "Temuco";
        this.region = new Region(9);
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
