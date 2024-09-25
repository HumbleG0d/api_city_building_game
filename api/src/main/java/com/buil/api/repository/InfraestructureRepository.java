package com.buil.api.repository;

import com.buil.api.model.infraestructure.Infraestructure;
import com.buil.api.model.infraestructure.TypeInfraestructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfraestructureRepository extends JpaRepository<Infraestructure, Long> {
  Infraestructure findByName(String name);
  List<Infraestructure> findInfraestructureByTypeInfraestructure(TypeInfraestructure typeInfraestructure);
}