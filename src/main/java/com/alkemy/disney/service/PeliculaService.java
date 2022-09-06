package com.alkemy.disney.service;

import com.alkemy.disney.dto.PeliculaBasicaDTO;
import com.alkemy.disney.dto.PeliculaDTO;

import java.util.List;

public interface PeliculaService {
    PeliculaDTO getDetailsById(Long id);

    PeliculaDTO update(Long id, PeliculaDTO peliculaDTO);

    void delete(Long id);

    PeliculaDTO save(PeliculaDTO peliculaDTO);

    List<PeliculaBasicaDTO> getDetailsByFilters(String titulo, Long genero, String orden);

    void addPersonaje(Long idPelicula, Long idPersonaje);

    void removePersonaje(Long idPelicula, Long idPersonaje);

}
