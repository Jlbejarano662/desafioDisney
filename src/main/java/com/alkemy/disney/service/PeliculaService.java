package com.alkemy.disney.service;

import com.alkemy.disney.dto.PeliculaDTO;

public interface PeliculaService {
    PeliculaDTO getDetailsById(Long id);

    PeliculaDTO update(Long id, PeliculaDTO peliculaDTO);

    void delete(Long id);

    PeliculaDTO save(PeliculaDTO peliculaDTO);

}
