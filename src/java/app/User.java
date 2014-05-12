/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Alexis Saavedra
 */
@ManagedBean
@SessionScoped
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String region;
    private String bloodGroup;
    private String bloodFactor;

    /**
     * Creates a new instance of Usuario
     */
    public User() {
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getBloodFactor() {
        return bloodFactor;
    }

    public void setBloodFactor(String bloodFactor) {
        this.bloodFactor = bloodFactor;
    }
    
    public String create(){
        // crea arrays con los campos y los valores a insertar en BD
        ArrayList fields = new ArrayList();
        ArrayList values = new ArrayList();
        
        // los campos y valores se deben añadir en el orden correlativo.
        fields.add("NAME"); values.add(this.name);
        fields.add("EMAIL"); values.add(this.email);
        fields.add("PASSWORD"); values.add(this.password);
        fields.add("REGION"); values.add(this.region);
        fields.add("BLOOD_GROUP"); values.add(this.bloodGroup);
        fields.add("BLOOD_FACTOR"); values.add(this.bloodFactor);
        
        // conecta a la bd
        DBConnection db = new DBConnection();
        // inserta los datos
        if (db.insert("User", fields, values)){
            String retString = "<div class=\"success\">";
            retString += "<p>Usuario creado con éxito.</p>";
            retString += "</div>";
            
            return retString;
        }
        else {
            String retString = "<div class=\"error\">";
            retString += "<p>Ha ocurrido un error en la creación de usuario.</p>";
            retString += "</div>";
            
            return retString;
        }
    }
}
