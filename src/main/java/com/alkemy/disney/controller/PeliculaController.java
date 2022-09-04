package com.alkemy.disney.controller;

import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("peliculas")
public class PeliculaController {

    private PeliculaService peliculaService;

    @Autowired
    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    //Detalle de una pelicula con sus personajes asociados
    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> getDetailsById(@PathVariable Long id) {
        PeliculaDTO peliculaDTO = peliculaService.getDetailsById(id);
        return ResponseEntity.ok().body(peliculaDTO);
    }

    //Editar una pelicula (solo se actualiza la entidad y no su listado de personajes)
    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> update(@PathVariable Long id, @RequestBody PeliculaDTO peliculaDTO) {
        PeliculaDTO resultado = peliculaService.update(id, peliculaDTO);
        return ResponseEntity.ok().body(resultado);
    }

    //Elimina una pelicula
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Guarda una pelicula con sus personajes
    @PostMapping
    public ResponseEntity<PeliculaDTO> save(@RequestBody PeliculaDTO peliculaDTO) {
        PeliculaDTO resultado = peliculaService.save(peliculaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

}
