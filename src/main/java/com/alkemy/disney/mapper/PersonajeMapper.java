package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.dto.PersonajeBasicoDTO;
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

    public PersonajeDTO personajeEntity2DTO(PersonajeEntity personajeEntity, boolean cargarPeliculas) {
        PersonajeDTO personajeDTO = new PersonajeDTO();
        personajeDTO.setId(personajeEntity.getId());
        personajeDTO.setImagen(personajeEntity.getImagen());
        personajeDTO.setNombre(personajeEntity.getNombre());
        personajeDTO.setEdad(personajeEntity.getEdad());
        personajeDTO.setHistoria(personajeEntity.getHistoria());
        personajeDTO.setPeso(personajeEntity.getPeso());
        if(cargarPeliculas) {
            List<PeliculaDTO> peliculaDTOS = peliculaMapper.peliculaEntityList2DTOList(personajeEntity.getPeliculas(), false);
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

    public void personajeRefreshValues(PersonajeEntity personaje, PersonajeDTO personajeDTO) {
        personaje.setImagen(personajeDTO.getImagen());
        personaje.setEdad(personajeDTO.getEdad());
        personaje.setNombre(personajeDTO.getNombre());
        personaje.setHistoria(personajeDTO.getHistoria());
        personaje.setPeso(personajeDTO.getPeso());
    }

    public List<PersonajeBasicoDTO> personajeEntity2ListDTOBasicoList(List<PersonajeEntity> personajeEntities) {
        List<PersonajeBasicoDTO> personajeBasicoDTOS = new ArrayList<>();
        for (PersonajeEntity personajeEntity : personajeEntities) {
            personajeBasicoDTOS.add(this.personajeEntity2DTOBasico(personajeEntity));
        }
        return personajeBasicoDTOS;
    }

    private PersonajeBasicoDTO personajeEntity2DTOBasico(PersonajeEntity personajeEntity) {
        PersonajeBasicoDTO personajeBasicoDTO = new PersonajeBasicoDTO();
        personajeBasicoDTO.setImagen(personajeEntity.getImagen());
        personajeBasicoDTO.setNombre(personajeEntity.getNombre());
        return personajeBasicoDTO;
    }

}
