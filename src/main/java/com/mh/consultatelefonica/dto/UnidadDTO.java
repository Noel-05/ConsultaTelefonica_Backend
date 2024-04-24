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
public class UnidadDTO {
    
    private Long id;
    
    private String code;
    private String name;
    private String address;
    
    private Long dependencia_id;
    private String dependencia_name;
    
}
