package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.entity.PeliculaEntity;
import com.alkemy.disney.mapper.PeliculaMapper;
import com.alkemy.disney.repository.PeliculaRepository;
import com.alkemy.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private PeliculaMapper peliculaMapper;

    //@Autowired
    //public PeliculaServiceImpl (PeliculaRepository peliculaRepository, PeliculaMapper peliculaMapper) {
        //this.peliculaRepository = peliculaRepository;
        //this.peliculaMapper = peliculaMapper;
    //}

    @Override
    public PeliculaDTO getDetailsById(Long id) {
        Optional<PeliculaEntity> peliculaEntity = peliculaRepository.findById(id);
        if (!peliculaEntity.isPresent()) {
            //throw new ParamNotFound("Id icono no valido");
            System.out.println("Id pelicula no valido");
        }
        PeliculaDTO peliculaDTO = peliculaMapper.peliculaEntity2DTO(peliculaEntity.get(), true);
        return peliculaDTO;
    }

    @Override
    public PeliculaDTO update(Long id, PeliculaDTO peliculaDTO) {
        Optional<PeliculaEntity> peliculaEntity = peliculaRepository.findById(id);
        if(!peliculaEntity.isPresent()) {
            //throw new ParamNotFound("Id pelicula no valido");
            System.out.println("Id pelicula no valido");
        }
        peliculaMapper.peliculaRefreshValues(peliculaEntity.get(),peliculaDTO);
        PeliculaEntity peliculaActualizada = peliculaRepository.save(peliculaEntity.get());
        PeliculaDTO resultado = peliculaMapper.peliculaEntity2DTO(peliculaActualizada,false);
        return resultado;
    }

    @Override
    public void delete(Long id) {
        peliculaRepository.deleteById(id);
    }

    @Override
    public PeliculaDTO save(PeliculaDTO peliculaDTO) {
        PeliculaEntity pelicula = peliculaMapper.peliculaDTO2Entity(peliculaDTO,true);
        System.out.println(pelicula.getPersonajes());
        PeliculaEntity peliculaGuardada = peliculaRepository.save(pelicula);
        PeliculaDTO resultado = peliculaMapper.peliculaEntity2DTO(peliculaGuardada,true);
        return resultado;
    }

}
