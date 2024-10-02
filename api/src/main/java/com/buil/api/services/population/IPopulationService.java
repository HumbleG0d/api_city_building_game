package com.buil.api.services.population;

import com.buil.api.model.Population;
import com.buil.api.request.UpdatePopulationRequest;


/**
 * Service interface for managing population-related operations.
 * Defines methods for adding, retrieving, updating, and deleting population entities.
 */
public interface IPopulationService {

    /**
     * Adds a new population to a specified city.
     *
     * @param name_city the name of the city to which the population will be added
     * @param total_inhabitants the total number of inhabitants to be added
     * @param costPerInhabitant the cost associated with each inhabitant
     * @return the created {@link Population} entity
     */
    Population addPopulation(String name_city, int total_inhabitants, int costPerInhabitant);

    /**
     * Retrieves a population entity by its ID.
     *
     * @param id the ID of the population to retrieve
     * @return the found {@link Population} entity
     */
    Population getPopulationById(Long id);

    /**
     * Retrieves a population entity associated with a specific city.
     *
     * @param city the name of the city whose population is to be retrieved
     * @return the found {@link Population} entity
     */
    Population getPopulationByCity(String city);

    /**
     * Updates an existing population based on the given request.
     *
     * @param populationRequest the request object containing updated population details
     * @return the updated {@link Population} entity
     */
    Population updatePopulation(UpdatePopulationRequest populationRequest);

    /**
     * Deletes a population entity by its ID.
     *
     * @param id the ID of the population to delete
     */
    void deletePopulation(Long id);

    /**
     * Creates an initial population with default settings.
     *
     * @return the created {@link Population} entity
     */
    Population initialPopulation();
}
