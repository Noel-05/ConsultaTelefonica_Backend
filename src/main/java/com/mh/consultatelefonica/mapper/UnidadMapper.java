/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mh.consultatelefonica.mapper;

import com.mh.consultatelefonica.dto.UnidadDTO;
import com.mh.consultatelefonica.model.Unidad;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 *
 * @author noel.renderos
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UnidadMapper {
    
    @Mapping(source = "unidad.dependencia.id", target = "dependencia_id")
    @Mapping(source = "unidad.dependencia.name", target = "dependencia_name")
    UnidadDTO unidadToDto(Unidad unidad);
    
    List<UnidadDTO> unidadToDtoList(List<Unidad> unidadList);
}
