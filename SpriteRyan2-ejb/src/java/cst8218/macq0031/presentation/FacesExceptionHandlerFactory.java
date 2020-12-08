/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.macq0031.presentation;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 *
 * @author ryan
 */
public class FacesExceptionHandlerFactory  extends ExceptionHandlerFactory{
    
    private ExceptionHandlerFactory parent;
    
    public FacesExceptionHandlerFactory(ExceptionHandlerFactory parent){
        this.parent = parent;
    }
    @Override
    public ExceptionHandler getExceptionHandler() {
       return new FacesExceptionHandler(parent.getExceptionHandler());
    }
    
}
