package com.alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "personaje")
@Getter
@Setter
@SQLDelete(sql = "UPDATE personaje SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class PersonajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagen;

    private String nombre;

    private Integer edad;

    private Float peso;

    private String historia;

    @ManyToMany(mappedBy = "personajes", fetch = FetchType.LAZY)
    private Set<PeliculaEntity> peliculas = new HashSet<>();

    private boolean deleted = Boolean.FALSE;

    //Añadir Personaje
    public void addPelicula(PeliculaEntity pelicula) {
        this.peliculas.add(pelicula);
    }

    //Eliminar Personaje
    public void removePelicula(PeliculaEntity pelicula) {
        this.peliculas.remove(pelicula);
    }

}
