package com.alkemy.disney.repository.specifications;

import com.alkemy.disney.dto.PeliculaFiltroDTO;
import com.alkemy.disney.entity.PeliculaEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaSpecification {

    public Specification<PeliculaEntity> getByFilters(PeliculaFiltroDTO filtroDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtroDTO.getTitulo())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("titulo")),
                                "%" + filtroDTO.getTitulo().toLowerCase() + "%"
                        )
                );
            }

            if (filtroDTO.getGenero() > 0) {
                predicates.add(
                        criteriaBuilder.equal(root.get("generoId"), filtroDTO.getGenero())
                );
            }

            //Remove duplicates
            query.distinct(true);

            //Order resolver
            String orderByField  = "fechaCreacion";
            query.orderBy(
                    filtroDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }

}
