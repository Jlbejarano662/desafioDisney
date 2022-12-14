package com.alkemy.disney.repository.specifications;

import com.alkemy.disney.dto.PersonajeFiltroDTO;
import com.alkemy.disney.entity.PeliculaEntity;
import com.alkemy.disney.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeSpecification {
    public Specification<PersonajeEntity> getByFilters(PersonajeFiltroDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getNombre())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filtersDTO.getNombre().toLowerCase() + "%"
                        )
                );
            }

            if (filtersDTO.getEdad() > 0) {
                predicates.add(
                        criteriaBuilder.equal(root.get("edad"), filtersDTO.getEdad())
                );
            }

            if (filtersDTO.getPeso() > 0) {
                predicates.add(
                        criteriaBuilder.equal(root.get("peso"), filtersDTO.getPeso())
                );
            }

            if (!CollectionUtils.isEmpty(filtersDTO.getPeliculas())) {
                Join<PeliculaEntity, PersonajeEntity> join = root.join("peliculas", JoinType.INNER);
                Expression<String> peliculasId = join.get("id");
                predicates.add(
                        peliculasId.in(filtersDTO.getPeliculas())
                );
            }

            //Remove duplicates
            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
