package com.alkemy.disney.service;

import com.alkemy.disney.dto.PersonajeBasicoDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.entity.PersonajeEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonajeService {

    Optional<PersonajeEntity> getPersonajeById(Long id);

    PersonajeDTO getDetailsById(Long id);

    PersonajeDTO update(Long id, PersonajeDTO personajeDTO);

    void delete(Long id);

    PersonajeDTO save(PersonajeDTO personajeDTO);

    List<PersonajeBasicoDTO> getByFilters(String nombre, Integer edad, Float peso, Set<Long> peliculas);

}
