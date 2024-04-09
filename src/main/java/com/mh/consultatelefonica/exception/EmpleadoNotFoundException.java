/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.exception;

/**
 *
 * @author noel.renderos
 */
public class EmpleadoNotFoundException extends RuntimeException {
    
    public EmpleadoNotFoundException(Long id){
        super("Couldn't find the employee with id :" + id);
    }
    
}
