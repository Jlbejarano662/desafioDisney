package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PersonajeFiltroDTO {

    private String nombre;

    private Integer edad;

    private Float peso;

    private Set<Long> peliculas;

    public PersonajeFiltroDTO(String nombre, Integer edad, Float peso, Set<Long> peliculas) {
        this.nombre = nombre;
        if(edad == null) {
            this.edad = Integer.valueOf(0);
        } else {
            this.edad = edad;
        }
        if(peso == null) {
            this.peso = Float.valueOf(0);
        }else {
            this.peso = peso;
        }
        this.peliculas = peliculas;
    }

}
