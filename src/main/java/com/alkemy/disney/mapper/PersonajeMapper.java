package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class PersonajeMapper {

    @Autowired
    private PeliculaMapper peliculaMapper;

    //@Autowired
    //public PersonajeMapper(PeliculaMapper peliculaMapper) {
        //this.peliculaMapper = peliculaMapper;
    //}

    public PersonajeDTO personajeEntity2DTO(PersonajeEntity personajeEntity, boolean cargarPeliculas) {
        PersonajeDTO personajeDTO = new PersonajeDTO();
        personajeDTO.setId(personajeEntity.getId());
        personajeDTO.setImagen(personajeEntity.getImagen());
        personajeDTO.setNombre(personajeEntity.getNombre());
        personajeDTO.setEdad(personajeEntity.getEdad());
        personajeDTO.setHistoria(personajeEntity.getHistoria());
        personajeDTO.setPeso(personajeEntity.getPeso());
        if(cargarPeliculas) {
            List<PeliculaDTO> peliculaDTOS = peliculaMapper.peliculaEntityList2DTOList(personajeEntity.getPeliculas(), cargarPeliculas);
            personajeDTO.setPeliculas(peliculaDTOS);
        }
        return personajeDTO;
    }

    public List<PersonajeDTO> personajeEntityList2DTOList(Set<PersonajeEntity> personajes, boolean cargarPeliculas) {
        List<PersonajeDTO> personajeDTOS = new ArrayList<>();
        for (PersonajeEntity personajeEntity : personajes) {
            personajeDTOS.add(this.personajeEntity2DTO(personajeEntity, cargarPeliculas));
        }
        return personajeDTOS;
    }


    public PersonajeEntity personajeDTO2Entity(PersonajeDTO personajeDTO, boolean cagarPeliculas) {
        PersonajeEntity personajeEntity = new PersonajeEntity();
        personajeEntity.setId(personajeDTO.getId());
        personajeEntity.setEdad(personajeDTO.getEdad());
        personajeEntity.setNombre(personajeDTO.getNombre());
        personajeEntity.setImagen(personajeDTO.getImagen());
        personajeEntity.setPeso(personajeDTO.getPeso());
        personajeEntity.setHistoria(personajeDTO.getHistoria());
        if (cagarPeliculas) {
            for (PeliculaDTO peliculaDTO : personajeDTO.getPeliculas()) {
                personajeEntity.addPelicula(peliculaMapper.peliculaDTO2Entity(peliculaDTO, false));
            }
        }
        return personajeEntity;
    }

}
