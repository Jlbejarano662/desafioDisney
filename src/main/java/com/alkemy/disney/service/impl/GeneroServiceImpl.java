package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.entity.GeneroEntity;
import com.alkemy.disney.mapper.GeneroMapper;
import com.alkemy.disney.repository.GeneroRepository;
import com.alkemy.disney.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServiceImpl implements GeneroService {

    private GeneroMapper generoMapper;

    private GeneroRepository generoRepository;

    @Autowired
    public GeneroServiceImpl(GeneroMapper generoMapper, GeneroRepository generoRepository) {
        this.generoMapper = generoMapper;
        this.generoRepository = generoRepository;
    }

    public GeneroDTO save(GeneroDTO dto) {
        GeneroEntity entity = generoMapper.generoDTO2Entity(dto);
        GeneroEntity entityGuardada = generoRepository.save(entity);
        GeneroDTO resultado = generoMapper.generoEntity2DTO(entityGuardada);
        return resultado;
    }

    public List<GeneroDTO> getAllGeneros() {
        List<GeneroEntity> entities = generoRepository.findAll();
        List<GeneroDTO> resultado = generoMapper.generoEntityList2DTOList(entities);
        return resultado;
    }

}
