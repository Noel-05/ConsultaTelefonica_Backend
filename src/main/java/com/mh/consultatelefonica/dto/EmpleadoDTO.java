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
public class EmpleadoDTO {
    
    private Long id;
    private String carnet;
    private String dui;
    private String first_name;
    private String last_name;
    private String birth_date;
    private String start_date;
    
    private Long puesto_id;
    private String puesto_name;
    
    private Long rol_id;
    private String rol_code;    
    private String rol_name;
    
    private Long unidad_id;
    private String unidad_name;
    
    private Long dependencia_id;
    private String dependencia_name;
}
