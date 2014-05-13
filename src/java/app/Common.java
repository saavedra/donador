/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

import javax.faces.bean.ManagedBean;
/**
 *
 * @author Alexis
 */

@ManagedBean
public class Common {
    public Common (){
        
    }
    
    private final String BASE_URL = "/Donador/faces/";
            
    private final String COMMON_IMAGE_FOLDER_PATH = "/Donador/faces/javax.faces.resource/img/";

    public String getBASE_URL() {
        return BASE_URL;
    }
    
    public String getCommonImagePath (String imgName){
        return this.COMMON_IMAGE_FOLDER_PATH + imgName;
    }
    
}
