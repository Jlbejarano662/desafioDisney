package com.alkemy.disney.controller;

import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("generos")
public class GeneroController {

    private GeneroService generoService;

    @Autowired
    public GeneroController(GeneroService generoService) {
        this.generoService  = generoService;
    }

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAll() {
        List<GeneroDTO> generosDTO = generoService.getAllGeneros();
        return ResponseEntity.ok().body(generosDTO);
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroDTO generoDTO) {
     GeneroDTO generoGuardado = generoService.save(generoDTO);
     return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }
}
