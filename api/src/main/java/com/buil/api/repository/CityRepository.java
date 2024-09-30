package com.buil.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.buil.api.model.City;


/**
 * Repository interface for managing {@link City} entities.
 * Extends JpaRepository to provide CRUD operations and custom queries for City entities.
 */
public interface CityRepository extends JpaRepository<City, Long> {

    /**
     * Retrieves a city by its name.
     *
     * @param name the name of the city to find
     * @return the {@link City} with the specified name, or null if no city is found
     */
    City findByName(String name);
}