/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

import java.sql.SQLException;

/**
 *
 * @author Alexis
 */
public class Person {
    private int id;
    private String name;
    private String surname; 
    private Commune commune;

    public Person() {
        commune = new Commune();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    
    public int getCommune() {
        return this.commune.getId();
    }

    public void setCommune(int commune_id) throws SQLException {
        this.commune = new Commune(commune_id);
    }
    
    
}
