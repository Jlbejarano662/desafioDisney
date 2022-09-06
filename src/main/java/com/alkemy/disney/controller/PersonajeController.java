package com.alkemy.disney.controller;

import com.alkemy.disney.dto.PersonajeBasicoDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("personajes")
public class PersonajeController {

    private PersonajeService personajeService;

    @Autowired
    public PersonajeController(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    //Detalle de un personaje con sus peliculas asociadas
    @GetMapping("{id}")
    public ResponseEntity<PersonajeDTO> getDetailsById(@PathVariable Long id) {
        PersonajeDTO personajeDTO = personajeService.getDetailsById(id);
        return ResponseEntity.ok().body(personajeDTO);
    }

    //Editar un personaje (solo se actualiza la entidad y no su listado de peliculas)
    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> update(@PathVariable Long id, @RequestBody PersonajeDTO personajeDTO) {
        PersonajeDTO resultado = personajeService.update(id, personajeDTO);
        return ResponseEntity.ok().body(resultado);
    }

    //Elimina un personaje
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Guarda un personaje (sin peliculas)
    @PostMapping
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO personajeDTO) {
        PersonajeDTO resultado = personajeService.save(personajeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    //Filtra el listado de personajes
    @GetMapping
    public ResponseEntity<List<PersonajeBasicoDTO>> getDetailsByFilters (
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer edad,
            @RequestParam(required = false) Float peso,
            @RequestParam(required = false) Set<Long> peliculas
    ) {
        List<PersonajeBasicoDTO> personajeBasicoDTOS = personajeService.getByFilters(nombre, edad, peso, peliculas);
        return ResponseEntity.ok().body(personajeBasicoDTOS);
    }

}
