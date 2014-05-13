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
public class Region {
    private int number;
    private String name;

    Region(int number){
        // carga la región desde la base de datos, de acuerdo con el nombre especificado
    }

    public int getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }

}