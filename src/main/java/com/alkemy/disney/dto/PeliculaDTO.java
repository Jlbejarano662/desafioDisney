package com.alkemy.disney.dto;

import com.alkemy.disney.entity.GeneroEntity;
import com.alkemy.disney.entity.PersonajeEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PeliculaDTO {

    private Long id;

    private String imagen;

    private String titulo;

    private String fechaCreacion;

    private Integer calificacion;

    private Long generoId;

    private List<PersonajeDTO> personajes;

}
