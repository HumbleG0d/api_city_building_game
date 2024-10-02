package com.buil.api.repository;

import com.buil.api.model.infraestructure.Infraestructure;
import com.buil.api.model.infraestructure.TypeInfraestructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Repository interface for managing {@link Infraestructure} entities.
 * Extends JpaRepository to provide CRUD operations and custom queries for Infraestructure entities.
 */
public interface InfraestructureRepository extends JpaRepository<Infraestructure, Long> {

  /**
   * Retrieves a list of infrastructure by its name.
   *
   * @param name the name of the infrastructure to find
   * @return a list of {@link Infraestructure} entities with the specified name
   */
  List<Infraestructure> findByName(String name);

  /**
   * Retrieves a list of infrastructure based on its type.
   *
   * @param typeInfraestructure the type of infrastructure to find (e.g., ranch, building, factory)
   * @return a list of {@link Infraestructure} entities matching the specified type
   */
  List<Infraestructure> findInfraestructureByTypeInfraestructure(TypeInfraestructure typeInfraestructure);
}