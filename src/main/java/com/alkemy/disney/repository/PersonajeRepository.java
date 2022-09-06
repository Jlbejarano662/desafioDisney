package com.alkemy.disney.repository;

import com.alkemy.disney.entity.PersonajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<PersonajeEntity, Long>, JpaSpecificationExecutor<PersonajeEntity> {
}
