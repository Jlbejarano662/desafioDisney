package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaFiltroDTO {

    private String titulo;

    private Long genero;

    private String orden;

    public PeliculaFiltroDTO(String titulo, Long genero, String orden) {
        this.titulo = titulo;
        if(genero == null){
            this.genero = Long.valueOf(0);
        } else {
            this.genero = genero;
        }
        this.orden = orden;
    }

    public boolean isASC() {
        return this.orden.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return this.orden.compareToIgnoreCase("DESC") == 0;
    }

}
