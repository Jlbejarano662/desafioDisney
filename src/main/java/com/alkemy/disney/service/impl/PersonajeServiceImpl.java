package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.PersonajeBasicoDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.dto.PersonajeFiltroDTO;
import com.alkemy.disney.entity.PersonajeEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.PersonajeMapper;
import com.alkemy.disney.repository.PersonajeRepository;
import com.alkemy.disney.repository.specifications.PersonajeSpecification;
import com.alkemy.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PersonajeSpecification personajeSpecification;

    @Override
    public Optional<PersonajeEntity> getPersonajeById(Long id) {
        Optional<PersonajeEntity> personaje = personajeRepository.findById( id);
        if(!personaje.isPresent()) {
            throw new ParamNotFound("Id personaje no valido");
            //System.out.println("Id personaje no valido");
        }
        return personaje;
    }

    @Override
    public PersonajeDTO getDetailsById(Long id) {
        Optional<PersonajeEntity> personajeEntity = personajeRepository.findById(id);
        if(!personajeEntity.isPresent()) {
            throw new ParamNotFound("Id personaje no valido");
            //System.out.println("Id personaje no valido");
        }
        PersonajeDTO personajeDTO = personajeMapper.personajeEntity2DTO(personajeEntity.get(), true);
        return personajeDTO;
    }

    @Override
    public PersonajeDTO update(Long id, PersonajeDTO personajeDTO) {
        Optional<PersonajeEntity> personajeEntity = personajeRepository.findById(id);
        if(!personajeEntity.isPresent()) {
            //throw new ParamNotFound("Id personaje no valido");
            System.out.println("Id personaje no valido");
        }
        personajeMapper.personajeRefreshValues(personajeEntity.get(), personajeDTO);
        PersonajeEntity personajeActualizado = personajeRepository.save(personajeEntity.get());
        PersonajeDTO resultado = personajeMapper.personajeEntity2DTO(personajeActualizado, true);
        return resultado;
    }

    @Override
    public void delete(Long id) {
        personajeRepository.deleteById(id);
    }

    @Override
    public PersonajeDTO save(PersonajeDTO personajeDTO) {
        PersonajeEntity personaje = personajeMapper.personajeDTO2Entity(personajeDTO,false);
        PersonajeEntity personajeGuardado = personajeRepository.save(personaje);
        PersonajeDTO resultado = personajeMapper.personajeEntity2DTO(personajeGuardado, true);
        return resultado;
    }

    @Override
    public List<PersonajeBasicoDTO> getByFilters(String nombre, Integer edad, Float peso, Set<Long> peliculas) {
        PersonajeFiltroDTO filtroDTO = new PersonajeFiltroDTO(nombre,edad,peso,peliculas);
        List<PersonajeEntity> personajeEntities = personajeRepository.findAll(personajeSpecification.getByFilters(filtroDTO));
        List<PersonajeBasicoDTO> personajeBasicoDTOS = personajeMapper.personajeEntity2ListDTOBasicoList(personajeEntities);
        return personajeBasicoDTOS;
    }

}
