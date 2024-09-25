package com.buil.api.services.city;

import java.util.List;

import com.buil.api.dto.CityDTO;
import com.buil.api.model.City;
import com.buil.api.request.AddCityRequest;
import com.buil.api.request.UpdateCityRequest;

public interface ICityService {
    CityDTO addCity(AddCityRequest cityRequest);
    CityDTO getCityById(Long id);
    City getCityByName(String name);
    List<City> getCityes();
    City updateCity(UpdateCityRequest city , Long id);
    void deleteCityById(Long id);
}
