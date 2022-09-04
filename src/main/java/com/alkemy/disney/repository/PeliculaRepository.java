package com.alkemy.disney.repository;

import com.alkemy.disney.entity.PeliculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long>, JpaSpecificationExecutor<PeliculaEntity> {
}
