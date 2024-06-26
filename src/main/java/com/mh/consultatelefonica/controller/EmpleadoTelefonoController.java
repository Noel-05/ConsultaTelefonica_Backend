/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.controller;

import com.mh.consultatelefonica.dto.EmpleadoTelefonoDTO;
import com.mh.consultatelefonica.model.EmpleadoTelefono;
import com.mh.consultatelefonica.service.EmpleadoTelefonoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author noel.renderos
 */
@RestController
@CrossOrigin("http://localhost:3000")
public class EmpleadoTelefonoController {
    
    @Autowired
    private EmpleadoTelefonoService empleadoTelefonoService;
    
    @PostMapping("/empleadoTelefono")
    public EmpleadoTelefono newEmpleadoTelefono(@RequestBody EmpleadoTelefonoDTO newEmpleadoTelefono){
        return empleadoTelefonoService.saveEmpleadoTelefono(newEmpleadoTelefono);
    }
    
    @GetMapping("/empleadoTelefonos")
    public List<EmpleadoTelefonoDTO> getEmpleadoTelefonos(){
        return empleadoTelefonoService.getEmpleadoTelefonos();
    }
    
    @GetMapping("/empleadoTelefono/{id}")
    public EmpleadoTelefonoDTO getEmpleadoTelefonoById(@PathVariable Long id){
        return empleadoTelefonoService.getEmpleadoTelefonoById(id);
    }
    
    @PutMapping("/empleadoTelefono/{id}")
    public EmpleadoTelefono updateEmpleadoTelefono(@RequestBody EmpleadoTelefonoDTO newEmpleadoTelefono, @PathVariable Long id){
        return empleadoTelefonoService.updateEmpleadoTelefono(newEmpleadoTelefono, id);
    }
    
    @DeleteMapping("/empleadoTelefono/{id}")
    public String deleteEmpleadoTelefono(@PathVariable Long id){
        return empleadoTelefonoService.deleteEmpleadoTelefono(id);
    }
    
    @GetMapping("/empleadoTelefono/empleado/{empleadoId}")
    public List<EmpleadoTelefonoDTO> getTelefonosByEmpleadoId(@PathVariable Long empleadoId){
        return empleadoTelefonoService.getTelefonosByEmpleadoId(empleadoId);
    }
}
