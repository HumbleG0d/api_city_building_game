package com.buil.api.services.population;

import com.buil.api.model.Population;
import com.buil.api.request.UpdatePopulationRequest;

public interface IPopulationService {
    Population addPopulation(String name_city, int total_inhabitants, int costPerInhabitant);
    Population getPopulationById(Long id);
    Population getPopulationByCity(String city);
    Population updatePopulation(UpdatePopulationRequest populationRequest);
    void deletePopulation(Long id);
    Population initialPopulation();
}
