/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author noel.renderos
 */
@Entity
@Getter
@Setter
public class Dependencia {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String code;
    private String name;
    private String address;
}
