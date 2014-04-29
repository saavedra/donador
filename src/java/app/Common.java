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
    
    private final String CommonImageFolderPath = "/Donador/faces/javax.faces.resource/img/";
    
    public String getCommonImagePath (String imgName){
        return this.CommonImageFolderPath + imgName;
    }
    
}
