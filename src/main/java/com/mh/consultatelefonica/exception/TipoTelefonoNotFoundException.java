/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.exception;

/**
 *
 * @author noel.renderos
 */
public class TipoTelefonoNotFoundException extends RuntimeException {
    
    public TipoTelefonoNotFoundException(Long id){
        super("Couldn't find the tipo telefono with id: " + id);
    }
}
