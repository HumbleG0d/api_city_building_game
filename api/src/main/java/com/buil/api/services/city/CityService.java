package com.buil.api.services.city;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.buil.api.dto.CityDTO;
import com.buil.api.dto.InfraestructureDTO;
import com.buil.api.dto.ResourceDTO;
import com.buil.api.exceptions.CityNotFoundException;
import com.buil.api.model.City;
import com.buil.api.repository.CityRepository;
import com.buil.api.request.AddCityRequest;
import com.buil.api.request.UpdateCityByName;
import com.buil.api.services.population.PopulationService;
import com.buil.api.services.resources.ResourceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
/**
 * Implementation of the {@link ICityService} interface for managing city-related operations.
 * Provides methods for creating, retrieving, updating, and deleting city entities.
 */
public class CityService implements ICityService {

    private final CityRepository cityRepository;
    private final PopulationService populationService;
    private final ResourceService resourceService;

    /**
     * Adds a new city based on the provided request.
     * Initializes resources and population for the city.
     *
     * @param cityRequest the request object containing city details to be added
     * @return a {@link CityDTO} representing the newly added city
     */
    @Override
    public CityDTO addCity(AddCityRequest cityRequest) {
        // Check if the city already exists, if not, create a new city
        City city = Optional.ofNullable(cityRepository.findByName(cityRequest.getName()))
                .orElseGet(() -> createCity(cityRequest));

        city = cityRepository.save(city);

        // Initialize resources and population for the new city
        var initial_resources = resourceService.initialResources(city);
        city.setResources(initial_resources);

        var population = populationService.initialPopulation();
        population.setCity(city);
        city.setPopulation(population);

        cityRepository.save(city);

        return convertToCityDTO(city);
    }

    /**
     * Helper method to create a new city from the provided request.
     *
     * @param cityRequest the request object with city data
     * @return the created {@link City} object
     */
    private City createCity(AddCityRequest cityRequest) {
        return new City(cityRequest.getName(), cityRequest.getResource(), cityRequest.getPopulation());
    }

    /**
     * Converts a {@link City} entity to a {@link CityDTO}.
     *
     * @param city the city entity to convert
     * @return a {@link CityDTO} object representing the city
     */
    private CityDTO convertToCityDTO(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setResource(city.getResources().stream()
                .map(resource -> new ResourceDTO(resource.getName(), resource.getTotal()))
                .collect(Collectors.toList()));
        cityDTO.setTotalPopulation(city.getPopulation().getTotal_inhabitants());
        cityDTO.setCoins(city.getCoins());

        // Map infrastructure data if available
        if (city.getInfraestructure() != null) {
            List<InfraestructureDTO> infrastructuresDTO = city.getInfraestructure().stream()
                    .map(infrastructure -> new InfraestructureDTO(
                            infrastructure.getName(),
                            infrastructure.getTypeInfraestructure(),
                            infrastructure.getCost(),
                            infrastructure.getTimeConstruction(),
                            infrastructure.getAbility()))
                    .collect(Collectors.toList());

            cityDTO.setInfraestructures(infrastructuresDTO);
        } else {
            city.setInfraestructure(Collections.emptyList());
        }
        return cityDTO;
    }

    /**
     * Retrieves a city by its ID.
     *
     * @param id the ID of the city to retrieve
     * @return a {@link CityDTO} representing the city
     * @throws CityNotFoundException if the city with the specified ID is not found
     */
    @Override
    public CityDTO getCityById(Long id) {
        var city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not found"));
        return convertToCityDTO(city);
    }

    /**
     * Retrieves a city by its name.
     *
     * @param name the name of the city to retrieve
     * @return a {@link CityDTO} representing the city
     */
    @Override
    public CityDTO getCityByName(String name) {
        return convertToCityDTO(cityRepository.findByName(name));
    }

    /**
     * Retrieves a list of all cities.
     *
     * @return a list of {@link CityDTO} objects representing all cities
     */
    @Override
    public List<CityDTO> getCityes() {
        return cityRepository.findAll().stream()
                .map(this::convertToCityDTO)
                .collect(Collectors.toList());
    }

    /**
     * Updates the name of an existing city by its ID.
     *
     * @param updateCityByName the request object containing updated city name
     * @param id the ID of the city to update
     * @return a {@link CityDTO} representing the updated city
     * @throws CityNotFoundException if the city with the specified ID is not found
     */
    @Override
    public CityDTO updateCity(UpdateCityByName updateCityByName, Long id) {
        return convertToCityDTO(cityRepository.findById(id)
                .map(existingCity -> updateExistingCityName(existingCity, updateCityByName))
                .map(cityRepository::save)
                .orElseThrow(() -> new CityNotFoundException("City not found")));
    }

    /**
     * Helper method to update the name of an existing city.
     *
     * @param existingCity the city to update
     * @param updateCityByName the object containing the new city name
     * @return the updated {@link City} object
     */
    private City updateExistingCityName(City existingCity, UpdateCityByName updateCityByName) {
        existingCity.setName(updateCityByName.getName());
        return existingCity;
    }

    /**
     * Deletes a city by its ID.
     *
     * @param id the ID of the city to delete
     * @throws CityNotFoundException if the city with the specified ID is not found
     */
    @Override
    public void deleteCityById(Long id) {
        cityRepository.findById(id)
                .ifPresentOrElse(cityRepository::delete, () -> {
                    throw new CityNotFoundException("City not found");
                });
    }
}
