/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

import java.io.IOException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alexis Saavedra
 */
@ManagedBean(name="user")
@SessionScoped
public class User extends Person {
    private int id;
    private String email;
    private String password;
    private String bloodGroup;
    private String bloodFactor;
    private boolean lastOperationStatus;
    private String lastOperationMessage;
    private boolean loggedIn;
    private String menu;

    /**
     * Creates a new instance of Usuario
     */
    public User() {
        this.loggedIn = false;
    }

    public void setId(int id){
        this.id = id;
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

    public String getLastOperationStatus() {
        if(lastOperationStatus)
            return "success";
        else return "error";
    }

    public String getLastOperationMessage() {
        return lastOperationMessage;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public String getMenu(){
        Menu menu = new Menu(this);
        return menu.display();
    }
    
    public void authenticate(){}
    
    public void logout() throws IOException{
        // invalida la sesión
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        // redirecciona
        Common common = new Common();
        FacesContext.getCurrentInstance().getExternalContext().redirect(common.getBASE_URL() + "index.xhtml");
    }
    
    
    
    
    public String create() throws IOException{
        // crea arrays con los campos y los valores a insertar en BD
        ArrayList fields = new ArrayList();
        ArrayList values = new ArrayList();
        
        // los campos y valores se deben añadir en el orden correlativo.
        fields.add("name"); values.add(this.getName());
        fields.add("email"); values.add(this.email);
        fields.add("password"); values.add(this.password);
        fields.add("region"); values.add(this.getCommune());
        fields.add("bloodGroup"); values.add(this.bloodGroup);
        fields.add("bloodFactor"); values.add(this.bloodFactor);
        
        // conecta a la bd
        DBConnection db = new DBConnection();
        // inserta los datos
        db.insert("User", fields, values);
            this.lastOperationMessage = db.getOperationMessage();
            this.lastOperationStatus = db.isOperationStatus();
            if( lastOperationStatus ){
                this.loggedIn = true;
                // redirecciona
                Common common = new Common();
                FacesContext.getCurrentInstance().getExternalContext().redirect(common.getBASE_URL() + "index.xhtml");
                return "Usuario creado con éxito: " + lastOperationMessage;
            }
            else{
                return "Ocurrió un error al crear el usuario: " + lastOperationMessage;
            }
    }
}
