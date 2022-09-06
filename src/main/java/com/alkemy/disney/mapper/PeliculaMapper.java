package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.PeliculaBasicaDTO;
import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entity.PeliculaEntity;
import com.alkemy.disney.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class PeliculaMapper {

    @Autowired
    private PersonajeMapper personajeMapper;

    public LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    public PeliculaDTO peliculaEntity2DTO(PeliculaEntity peliculaEntity, boolean cargarPersonajes) {
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        peliculaDTO.setId(peliculaEntity.getId());
        peliculaDTO.setImagen(peliculaEntity.getImagen());
        peliculaDTO.setTitulo(peliculaEntity.getTitulo());
        peliculaDTO.setCalificacion(peliculaEntity.getCalificacion());
        peliculaDTO.setFechaCreacion(peliculaEntity.getFechaCreacion().toString());
        peliculaDTO.setGeneroId(peliculaEntity.getGeneroId());
        if (cargarPersonajes) {
            List<PersonajeDTO> personajeDTOS = personajeMapper.personajeEntityList2DTOList(peliculaEntity.getPersonajes(), false);
            peliculaDTO.setPersonajes(personajeDTOS);
        }
        return peliculaDTO;
    }

    public List<PeliculaDTO> peliculaEntityList2DTOList(Set<PeliculaEntity> peliculas, boolean cargarPersonajes) {
        List<PeliculaDTO> peliculaDTOS = new ArrayList<>();
        for (PeliculaEntity peliculaEntity: peliculas) {
            peliculaDTOS.add(this.peliculaEntity2DTO(peliculaEntity,cargarPersonajes));
        }
        return peliculaDTOS;
    }

    public void peliculaRefreshValues(PeliculaEntity peliculaEntity, PeliculaDTO peliculaDTO) {
        peliculaEntity.setImagen(peliculaDTO.getImagen());
        peliculaEntity.setTitulo(peliculaDTO.getTitulo());
        peliculaEntity.setCalificacion(peliculaDTO.getCalificacion());
        peliculaEntity.setFechaCreacion(
            this.string2LocalDate(peliculaDTO.getFechaCreacion())
        );
        peliculaEntity.setGeneroId(peliculaDTO.getGeneroId());
    }

    public PeliculaEntity peliculaDTO2Entity(PeliculaDTO peliculaDTO, boolean cargarPersonajes) {
        PeliculaEntity peliculaEntity = new PeliculaEntity();
        peliculaEntity.setImagen(peliculaDTO.getImagen());
        peliculaEntity.setTitulo(peliculaDTO.getTitulo());
        peliculaEntity.setCalificacion(peliculaDTO.getCalificacion());
        peliculaEntity.setFechaCreacion(
                this.string2LocalDate(peliculaDTO.getFechaCreacion())
        );
        if (cargarPersonajes) {
            for (PersonajeDTO personajeDTO : peliculaDTO.getPersonajes()) {
                peliculaEntity.addPersonaje(personajeMapper.personajeDTO2Entity(personajeDTO, false));
            }
        }
        peliculaEntity.setGeneroId(peliculaDTO.getGeneroId());
        return peliculaEntity;
    }

    public List<PeliculaBasicaDTO> peliculaEntityList2DTOBasicList(List<PeliculaEntity> peliculaEntities) {
        List<PeliculaBasicaDTO> peliculaBasicaDTOS = new ArrayList<>();
        for (PeliculaEntity peliculaEntity : peliculaEntities) {
            peliculaBasicaDTOS.add(this.peliculaEntityDTOBasico(peliculaEntity));
        }
        return peliculaBasicaDTOS;
    }

    private PeliculaBasicaDTO peliculaEntityDTOBasico(PeliculaEntity peliculaEntity) {
        PeliculaBasicaDTO peliculaBasicaDTO = new PeliculaBasicaDTO();
        peliculaBasicaDTO.setImagen(peliculaEntity.getImagen());
        peliculaBasicaDTO.setTitulo(peliculaEntity.getTitulo());
        peliculaBasicaDTO.setFechaCreacion(peliculaEntity.getFechaCreacion().toString());
        return peliculaBasicaDTO;
    }

}
