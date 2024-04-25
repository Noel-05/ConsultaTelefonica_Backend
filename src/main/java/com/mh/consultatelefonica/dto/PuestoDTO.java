/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author noel.renderos
 */
@Getter
@Setter
public class PuestoDTO {
    private Long id;
    
    private String name;
    private double salary;
    
    private Long rol_id;
    private String rol_code;
    private String rol_name;
    
    private Long unidad_id;
    private String unidad_code;
    private String unidad_name;
}
