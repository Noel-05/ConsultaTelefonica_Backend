/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author noel.renderos
 */
@Entity
@Getter
@Setter
public class Empleado {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String carnet;
    private String dui;
    private String first_name;
    private String last_name;
    private Date birth_date;
    private Date start_date;
    
    @ManyToOne
    @JoinColumn(name = "puesto_id")
    private Puesto puesto;
}
