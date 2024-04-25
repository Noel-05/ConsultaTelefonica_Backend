/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.controller;

import com.mh.consultatelefonica.dto.EmpleadoDTO;
import com.mh.consultatelefonica.model.Empleado;
import com.mh.consultatelefonica.service.EmpleadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author noel.renderos
 */
@RestController
@CrossOrigin("http://localhost:3000")
public class EmpleadoController {
    
    @Autowired
    private EmpleadoService empleadoService;
    
    @PostMapping("/empleado/{puestoId}")
    public Empleado newEmpleado(@RequestBody Empleado newEmpleado, @PathVariable Long puestoId){
        return empleadoService.saveEmpleado(newEmpleado, puestoId);
    }
    
    @GetMapping("/empleados")
    public List<EmpleadoDTO> getEmpleados(){
        return empleadoService.getEmpleados();
    }
    
    @GetMapping("/empleado/{id}")
    public EmpleadoDTO getEmpleadoById(@PathVariable Long id){
        return empleadoService.getEmpleadoById(id);
    }
    
    @PutMapping("/empleado/{puestoId}/{id}")
    public Empleado updateEmpleado(@RequestBody Empleado newEmpleado, @PathVariable("puestoId") Long puestoId, @PathVariable("id") Long id){
        return empleadoService.updateEmpleado(newEmpleado, puestoId, id);
    }
    
    @DeleteMapping("/empleado/{id}")
    public String deleteEmployee(@PathVariable Long id){
        return empleadoService.deleteEmployee(id);
    }
    
    @GetMapping("/empleado")
    public List<EmpleadoDTO> filterEmpleados(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname){
        return empleadoService.filterEmpleados(firstname, lastname);
    }
    
    @GetMapping("/empleado/telefonos")
    public List<EmpleadoDTO> getEmpleadosConTelefonos(){
        return empleadoService.getEmpleadosConTelefonos();
    }
    
    @GetMapping("/empleado/unidad/{unidadId}")
    public List<EmpleadoDTO> getEmpleadosByUnidad(@PathVariable Long unidadId){
        return empleadoService.getEmpleadosByUnidad(unidadId);
    }
}
