package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.PeliculaBasicaDTO;
import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.dto.PeliculaFiltroDTO;
import com.alkemy.disney.entity.PeliculaEntity;
import com.alkemy.disney.entity.PersonajeEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.PeliculaMapper;
import com.alkemy.disney.repository.PeliculaRepository;
import com.alkemy.disney.repository.specifications.PeliculaSpecification;
import com.alkemy.disney.service.PeliculaService;
import com.alkemy.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Autowired
    private PeliculaSpecification peliculaSpecification;

    @Autowired
    private PersonajeService personajeService;

    @Override
    public PeliculaDTO getDetailsById(Long id) {
        Optional<PeliculaEntity> peliculaEntity = peliculaRepository.findById(id);
        if (!peliculaEntity.isPresent()) {
            throw new ParamNotFound("Id pelicula no valido");
            //System.out.println("Id pelicula no valido");
        }
        PeliculaDTO peliculaDTO = peliculaMapper.peliculaEntity2DTO(peliculaEntity.get(), true);
        return peliculaDTO;
    }

    @Override
    public PeliculaDTO update(Long id, PeliculaDTO peliculaDTO) {
        Optional<PeliculaEntity> peliculaEntity = peliculaRepository.findById(id);
        if(!peliculaEntity.isPresent()) {
            throw new ParamNotFound("Id pelicula no valido");
            //System.out.println("Id pelicula no valido");
        }
        peliculaMapper.peliculaRefreshValues(peliculaEntity.get(),peliculaDTO);
        PeliculaEntity peliculaActualizada = peliculaRepository.save(peliculaEntity.get());
        PeliculaDTO resultado = peliculaMapper.peliculaEntity2DTO(peliculaActualizada,true);
        return resultado;
    }

    @Override
    public void delete(Long id) {

        peliculaRepository.deleteById(id);
    }

    @Override
    public PeliculaDTO save(PeliculaDTO peliculaDTO) {
        PeliculaEntity pelicula = peliculaMapper.peliculaDTO2Entity(peliculaDTO,true);
        PeliculaEntity peliculaGuardada = peliculaRepository.save(pelicula);
        PeliculaDTO resultado = peliculaMapper.peliculaEntity2DTO(peliculaGuardada,true);
        return resultado;
    }

    @Override
    public List<PeliculaBasicaDTO> getDetailsByFilters(String titulo, Long genero, String orden) {
        PeliculaFiltroDTO filtroDTO = new PeliculaFiltroDTO(titulo, genero, orden);
        List<PeliculaEntity> peliculaEntities = peliculaRepository.findAll(peliculaSpecification.getByFilters(filtroDTO));
        List<PeliculaBasicaDTO> peliculaBasicaDTOS = peliculaMapper.peliculaEntityList2DTOBasicList(peliculaEntities);
        return peliculaBasicaDTOS;
    }

    @Override
    public void addPersonaje(Long idPelicula, Long idPersonaje) {
        Optional<PeliculaEntity> pelicula = peliculaRepository.findById(idPelicula);
        Optional<PersonajeEntity> personaje = personajeService.getPersonajeById(idPersonaje);
        pelicula.get().addPersonaje(personaje.get());
        peliculaRepository.save(pelicula.get());
    }

    @Override
    public void removePersonaje(Long idPelicula, Long idPersonaje) {
        Optional<PeliculaEntity> pelicula = peliculaRepository.findById(idPelicula);
        Optional<PersonajeEntity> personaje = personajeService.getPersonajeById(idPersonaje);
        pelicula.get().removePersonaje(personaje.get());
        peliculaRepository.save(pelicula.get());
    }

}
