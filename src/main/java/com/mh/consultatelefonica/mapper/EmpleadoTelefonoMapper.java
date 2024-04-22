/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mh.consultatelefonica.mapper;

import com.mh.consultatelefonica.dto.EmpleadoTelefonoDTO;
import com.mh.consultatelefonica.model.EmpleadoTelefono;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 *
 * @author noel.renderos
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmpleadoTelefonoMapper {
    
    @Mapping(source = "empleado.id", target = "empleado_id")
    @Mapping(source = "tipo_telefono.id", target = "tipo_telefono_id")
    @Mapping(source = "tipo_telefono.name", target = "tipo_telefono_name")
    EmpleadoTelefonoDTO empleadoTelefonoToDto(EmpleadoTelefono empleadoTelefono);
    
    List<EmpleadoTelefonoDTO> empleadoTelefonoToDtoList(List<EmpleadoTelefono> empleadoTelefonoList);
}