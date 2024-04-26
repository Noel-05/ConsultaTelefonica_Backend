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
public class EmpleadoTelefonoDTO {
    
    private Long id;
    private String phone_number;
    
    private Long empleado_id;
    private String empleado_first_name;
    private String empleado_last_name;
    
    private Long tipo_telefono_id;
    private String tipo_telefono_name;

}
