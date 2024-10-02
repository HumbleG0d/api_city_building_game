package com.buil.api.services.city;

import java.util.List;

import com.buil.api.dto.CityDTO;
import com.buil.api.request.AddCityRequest;
import com.buil.api.request.UpdateCityByName;

/**
 * Service interface for managing city-related operations.
 * Defines the contract for handling city data, including creation, retrieval, updating, and deletion of cities.
 */
public interface ICityService {

    /**
     * Adds a new city based on the provided request.
     *
     * @param cityRequest the request object containing city details to be added
     * @return a {@link CityDTO} object representing the newly added city
     */
    CityDTO addCity(AddCityRequest cityRequest);

    /**
     * Retrieves a city by its ID.
     *
     * @param id the ID of the city to retrieve
     * @return a {@link CityDTO} object representing the city with the specified ID
     */
    CityDTO getCityById(Long id);

    /**
     * Retrieves a city by its name.
     *
     * @param name the name of the city to retrieve
     * @return a {@link CityDTO} object representing the city with the specified name
     */
    CityDTO getCityByName(String name);

    /**
     * Retrieves a list of all cities.
     *
     * @return a list of {@link CityDTO} objects representing all cities
     */
    List<CityDTO> getCityes();

    /**
     * Updates the details of an existing city.
     *
     * @param updateCityByName the request object containing updated city details
     * @param id the ID of the city to be updated
     * @return a {@link CityDTO} object representing the updated city
     */
    CityDTO updateCity(UpdateCityByName updateCityByName, Long id);

    /**
     * Deletes a city by its ID.
     *
     * @param id the ID of the city to be deleted
     */
    void deleteCityById(Long id);
}
