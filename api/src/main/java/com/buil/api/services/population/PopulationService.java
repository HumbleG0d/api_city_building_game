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
public class PopulationService implements IPopulationService {

    private final CityRepository cityRepository;
    private final PopulationRepository populationRepository;

    @Override
    public Population addPopulation(String name_city, int total_inhabitants, int costPerInhabitant) {
        var city = cityRepository.findByName(name_city);
        BigDecimal cantidad_habitantes = new BigDecimal(total_inhabitants);
        BigDecimal cost_per_Inhabitant = new BigDecimal(total_inhabitants);
        BigDecimal costo_total = cantidad_habitantes.multiply(cost_per_Inhabitant);

        /*TODO:Falta usar los metodos de caracteristica servico , en esta servicio*/
        if (city.getCoins().compareTo(costo_total) > 0) {
            city.setCoins(city.getCoins().subtract(costo_total));
            var update_population = updatePopulation(city, new UpdatePopulationRequest(
                    city.getPopulation().getTotal_inhabitants() + total_inhabitants, 2.0, 2.0));
            city.setPopulation(update_population);
            cityRepository.save(city);
            return update_population;
        } else {

            System.out.println("No tienes suficiente dinero.");
        }
        return null;
    }

    
    private Population updatePopulation(City current_city ,  UpdatePopulationRequest updatePopulationRequest) {
         current_city.getPopulation().setTotal_inhabitants(updatePopulationRequest.getTotal_inhabitants());
         current_city.getPopulation().setGrowth(updatePopulationRequest.getGrowth());
         current_city.getPopulation().setPbi(updatePopulationRequest.getPbi());
        return  current_city.getPopulation();
    }

    @Override
    public Population getPopulationById(Long id) {
        return populationRepository.findById(id)
                .orElseThrow(() -> new PopulationNotFoundException("Pupulation not found"));
    }

    @Override
    public Population getPopulationByCity(String city) {
        return populationRepository.findByCityName(city);
    }

    @Override
    public Population updatePopulation(UpdatePopulationRequest populationRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePopulation'");
    }

    @Override
    public void deletePopulation(Long id) {
        populationRepository.findById(id).ifPresentOrElse(populationRepository::delete, () -> {
            throw new PopulationNotFoundException("Population not found");
        });
    }

    @Override
    public Population initialPopulation() {
        return new Population(2, 0, 0);
    }
    
}
