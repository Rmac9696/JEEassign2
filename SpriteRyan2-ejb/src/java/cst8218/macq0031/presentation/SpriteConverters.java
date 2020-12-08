/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.macq0031.presentation;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author ryan
 * a managed bean containing custom converters & validators for sprite validation & conversion
 */
@ManagedBean 
public class SpriteConverters {

    /**
     *  a converter class for taking strings in rgb or hex format and converting them into Color.class 
     *  or Converting a Color object into a string of rrr,ggg,bbb format
     */
    @FacesConverter(forClass = Color.class)
    public static class ColorConverter implements Converter {

        /**
         * 
         * Accepts a string in either hex or rgb format and parses a Color object
         * @param fc
         * @param uic
         * @param string
         * @return color new color 
         */
        @Override
        public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
            Pattern rgb = Pattern.compile("^((25[0-5])|(2[0-4][0-9])|(1[0-9][0-9])|([1-9][0-9])|([0-9])),((25[0-5])|(2[0-4][0-9])|(1[0-9][0-9])|([1-9][0-9])|([0-9])),((25[0-5])|(2[0-4][0-9])|(1[0-9][0-9])|([1-9][0-9])|([0-9])){1}$"); // match rgb
            Pattern hex = Pattern.compile("^(#{0,1}([A-F]|[a-f]|[0-9]){6}){1}$"); // match hex
            Matcher r = rgb.matcher(string);
            Matcher h = hex.matcher(string);
            if(r.matches()){
                String rgbVal[] = string.split(",");
                Color color = new Color(Integer.parseInt(rgbVal[0]),Integer.parseInt(rgbVal[1]),Integer.parseInt(rgbVal[2]));
                return color;
            }
            else if(h.matches()) {
                String hexVal = string.replace("#", "");
                hexVal = hexVal.toUpperCase();
                Color color = new Color(Integer.parseInt(hexVal,16));
                return color;
            }
            else{
                FacesMessage msg = new FacesMessage("Error Converting to color","Invalid color format");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ConverterException(msg);
            }
        }
        /**
         * 
         * @param fc
         * @param uic 
         * @param t
         * @return color as a string in format rrr,ggg,bbb
         */
        @Override
        public String getAsString(FacesContext fc, UIComponent uic, Object t) {
            
            Color c = (Color)t;
            return  String.valueOf(c.getRed()) + "," + String.valueOf(c.getGreen()) + "," + String.valueOf(c.getBlue()) ;
        }
        
    }
   
}
