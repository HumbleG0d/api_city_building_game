package com.buil.api.services.city;

import java.util.List;

import com.buil.api.dto.CityDTO;
import com.buil.api.request.AddCityRequest;
import com.buil.api.request.UpdateCityByName;

public interface ICityService {
    CityDTO addCity(AddCityRequest cityRequest);
    CityDTO getCityById(Long id);
    CityDTO getCityByName(String name);
    List<CityDTO> getCityes();
    CityDTO updateCity(UpdateCityByName updateCityByName , Long id);
    void deleteCityById(Long id);
}
