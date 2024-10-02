package com.buil.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buil.api.model.Population;

/**
 * Repository interface for managing {@link Population} entities.
 * Extends JpaRepository to provide CRUD operations and custom queries for Population entities.
 */
public interface PopulationRepository extends JpaRepository<Population, Long> {

    /**
     * Retrieves the population associated with a city by the city's name.
     *
     * @param nameCity the name of the city whose population is to be found
     * @return the {@link Population} associated with the specified city name, or null if no population is found
     */
    Population findByCityName(String nameCity);
}
