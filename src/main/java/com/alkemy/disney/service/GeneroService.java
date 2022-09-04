package com.alkemy.disney.service;

import com.alkemy.disney.dto.GeneroDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

public interface GeneroService {
    List<GeneroDTO> getAllGeneros();

    GeneroDTO save(GeneroDTO generoDTO);

}