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
import com.buil.api.request.UpdateCityRequest;
import com.buil.api.services.population.PopulationService;
import com.buil.api.services.resources.ResourceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService implements ICityService {

    private final CityRepository cityRepository;

    private final PopulationService populationService;
  
    private final ResourceService resourceService;


    @Override
    public CityDTO addCity(AddCityRequest cityRequest) {

        City city = Optional.ofNullable(cityRepository.findByName(cityRequest.getName()))
                .orElseGet(() -> createCity(cityRequest));

        city = cityRepository.save(city);

        var initial_reosurce = resourceService.initialResources(city);
        city.setResources(initial_reosurce);

        var poblacion = populationService.initialPopulation();
        poblacion.setCity(city);
        city.setPopulation(poblacion);

        cityRepository.save(city);

        return convertToCityDTO(city);
    }
    

    private City createCity(AddCityRequest cityRequest) {
        return new City(
                cityRequest.getName(), cityRequest.getResource(), cityRequest.getPopulation());
    }

      
    private CityDTO convertToCityDTO(City city) {
        CityDTO cityDTO = new CityDTO();

        cityDTO.setId(city.getId());

        cityDTO.setName(city.getName());

        cityDTO.setResource(city.getResources().stream().map(resource -> new ResourceDTO(resource.getName(), resource.getTotal())).collect(Collectors.toList()));

        cityDTO.setTotalPopulation(city.getPopulation().getTotal_inhabitants());

        cityDTO.setCoins(city.getCoins());


        if (city.getInfraestructure() != null) {
        List<InfraestructureDTO> infraestructuresDTO = city.getInfraestructure().stream()
                .map(infraestructure -> new InfraestructureDTO(
                        infraestructure.getName(),
                        infraestructure.getTypeInfraestructure(),
                        infraestructure.getCost(),
                        infraestructure.getTimeConstruction(),
                        infraestructure.getAbility()
                ))
                .collect(Collectors.toList());

        cityDTO.setInfraestructures(infraestructuresDTO);
        }else
        {
            city.setInfraestructure(Collections.emptyList());
        }
        return cityDTO;
    }

    @Override
    public CityDTO getCityById(Long id) {
        var city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException("City not found"));
        return convertToCityDTO(city);
    }

    @Override
    public City getCityByName(String name) {
        return cityRepository.findByName(name);
    }

    @Override
    public List<City> getCityes() {
        return cityRepository.findAll();
    }

    @Override
    public City updateCity(UpdateCityRequest city, Long id) {
        return cityRepository.findById(id).map(existingCity -> updateExistingCity(existingCity , city)).map(cityRepository::save).orElseThrow(() -> new CityNotFoundException("Ciudad no encontrada"));
    }
    
    private City updateExistingCity(City existingCity , UpdateCityRequest updateCity) {
        existingCity.setName(updateCity.getName());
        existingCity.setPopulation(updateCity.getPopulation());
        existingCity.setResources(updateCity.getResources());
        return existingCity;
    }


    @Override
    public void deleteCityById(Long id) {
        cityRepository.findById(id).ifPresentOrElse(cityRepository::delete, () -> {
            throw new CityNotFoundException("City not found");
       });
    }
    
}
