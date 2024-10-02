package com.buil.api.services.population;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.buil.api.exceptions.PopulationNotFoundException;
import com.buil.api.model.City;
import com.buil.api.model.Population;
import com.buil.api.repository.CityRepository;
import com.buil.api.repository.PopulationRepository;
import com.buil.api.request.UpdatePopulationRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
/**
 * Implementation of the {@link IPopulationService} interface.
 * Provides operations related to managing population entities, such as adding, retrieving, updating, and deleting populations.
 */
public class PopulationService implements IPopulationService {

    private final CityRepository cityRepository;
    private final PopulationRepository populationRepository;

    /**
     * Adds a new population to a specified city.
     * Deducts the cost based on the number of inhabitants and the cost per inhabitant.
     *
     * @param name_city the name of the city to which the population will be added
     * @param total_inhabitants the total number of inhabitants to be added
     * @param costPerInhabitant the cost associated with each inhabitant
     * @return the updated {@link Population} entity, or {@code null} if the city does not have enough funds
     */
    @Override
    public Population addPopulation(String name_city, int total_inhabitants, int costPerInhabitant) {
        var city = cityRepository.findByName(name_city);
        BigDecimal cantidad_habitantes = new BigDecimal(total_inhabitants);
        BigDecimal cost_per_Inhabitant = new BigDecimal(costPerInhabitant);
        BigDecimal costo_total = cantidad_habitantes.multiply(cost_per_Inhabitant);

        // TODO: Missing integration with characteristic service

        if (city.getCoins().compareTo(costo_total) > 0) {
            city.setCoins(city.getCoins().subtract(costo_total));
            var updated_population = updatePopulation(city, new UpdatePopulationRequest(
                    city.getPopulation().getTotal_inhabitants() + total_inhabitants, 2.0, 2.0));
            city.setPopulation(updated_population);
            cityRepository.save(city);
            return updated_population;
        } else {
            System.out.println("Insufficient funds.");
            return null;
        }
    }

    /**
     * Updates the population for a given city with the provided update request.
     *
     * @param current_city the city whose population will be updated
     * @param updatePopulationRequest the request containing updated population details
     * @return the updated {@link Population} entity
     */
    private Population updatePopulation(City current_city, UpdatePopulationRequest updatePopulationRequest) {
        current_city.getPopulation().setTotal_inhabitants(updatePopulationRequest.getTotal_inhabitants());
        current_city.getPopulation().setGrowth(updatePopulationRequest.getGrowth());
        current_city.getPopulation().setPbi(updatePopulationRequest.getPbi());
        return current_city.getPopulation();
    }

    /**
     * Retrieves a population entity by its ID.
     *
     * @param id the ID of the population to retrieve
     * @return the found {@link Population} entity
     * @throws PopulationNotFoundException if no population is found with the given ID
     */
    @Override
    public Population getPopulationById(Long id) {
        return populationRepository.findById(id)
                .orElseThrow(() -> new PopulationNotFoundException("Population not found"));
    }

    /**
     * Retrieves a population entity associated with a specific city.
     *
     * @param city the name of the city whose population is to be retrieved
     * @return the found {@link Population} entity
     */
    @Override
    public Population getPopulationByCity(String city) {
        return populationRepository.findByCityName(city);
    }

    /**
     * Updates a population based on the given request.
     *
     * @param populationRequest the request object containing updated population details
     * @return the updated {@link Population} entity
     * @throws UnsupportedOperationException if the method is not yet implemented
     */
    @Override
    public Population updatePopulation(UpdatePopulationRequest populationRequest) {
        throw new UnsupportedOperationException("Unimplemented method 'updatePopulation'");
    }

    /**
     * Deletes a population entity by its ID.
     *
     * @param id the ID of the population to delete
     * @throws PopulationNotFoundException if no population is found with the given ID
     */
    @Override
    public void deletePopulation(Long id) {
        populationRepository.findById(id).ifPresentOrElse(populationRepository::delete, () -> {
            throw new PopulationNotFoundException("Population not found");
        });
    }

    /**
     * Creates an initial population with default values.
     *
     * @return the created {@link Population} entity
     */
    @Override
    public Population initialPopulation() {
        return new Population(2, 0, 0);
    }
}
