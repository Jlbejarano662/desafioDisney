package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.entity.GeneroEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeneroMapper {
    public GeneroEntity generoDTO2Entity(GeneroDTO dto) {
        GeneroEntity generoEntity = new GeneroEntity();
        generoEntity.setImagen(dto.getImagen());
        generoEntity.setNombre(dto.getNombre());
        return generoEntity;
    }

    public GeneroDTO generoEntity2DTO(GeneroEntity entityGuardada) {
        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setId(entityGuardada.getId());
        generoDTO.setImagen(entityGuardada.getImagen());
        generoDTO.setNombre(entityGuardada.getNombre());
        return generoDTO;
    }

    public List<GeneroDTO> generoEntityList2DTOList(List<GeneroEntity> entities) {
        List<GeneroDTO> generoDTOS = new ArrayList<>();
        for (GeneroEntity genero : entities) {
            generoDTOS.add(generoEntity2DTO(genero));
        }
        return  generoDTOS;
    }
}
