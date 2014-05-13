/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Alexis Saavedra
 */
public class Menu {
    private String menu;
    private ArrayList elements;
    private User activeUser;
    
    Menu(User user){
        this.elements = new ArrayList();
        this.activeUser = user;
    }
    
    /**
     * Clears the variables to generate a new menu
     */
    private void flush(){
        this.menu = "";
        this.elements.clear();
    }
    
    /**
     * Converts the ArratList to String
     */
    private void generate(){
        Iterator i = elements.iterator();
        while(i.hasNext()){
            Object element = i.next();
            menu += element;
        }
    }
    
    private String makeLink(String href, String text){
        Common common = new Common();
        return "<a href=\""+ common.getBASE_URL() + href + "\">"+text+"</a>";
    }
    
    
    /**
     * Entrega el menú como un string, listo para ser desplegado
     * @return 
     */
    public String display(){        
        if(this.activeUser.isLoggedIn()){
            this.flush();
            this.elements.add("<li>" + makeLink("#", "Mi cuenta") + "</li>");
            this.elements.add("<li>" + makeLink("accounts/logout.xhtml", "Logout") + "</li>");
            this.generate();
        }
        // if not logged in
        else{
            this.flush();
            this.elements.add("<li>" + makeLink("accounts/register.xhtml", "Quiero donar") + "</li>");
            this.elements.add("<li>" + makeLink("accounts/register.xhtml", "Necesito donantes") + "</li>");
            this.elements.add("<li>" + makeLink("accounts/login.xhtml", "Ingresar") + "</li>");
            this.generate();
        }
        
        return menu;
    }
    
}
