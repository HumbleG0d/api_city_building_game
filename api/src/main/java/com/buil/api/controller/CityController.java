package com.buil.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buil.api.dto.CityDTO;
import com.buil.api.exceptions.CityNotFoundException;
import com.buil.api.request.AddCityRequest;
import com.buil.api.request.UpdateCityByName;
import com.buil.api.response.ApiResponse;
import com.buil.api.services.city.CityService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;






@RequestMapping("${api.prefix}/cities")
@RestController
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CityDTO>> addCity(@RequestBody AddCityRequest addCityRequest) {
        try {
            CityDTO city = cityService.addCity(addCityRequest);
            return ResponseEntity.ok(new ApiResponse<>("Add city success", city));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(e.getMessage(), null));
        }
    }
    
    @GetMapping("/{id}/city")
    public ResponseEntity<ApiResponse<CityDTO>> getCity(@PathVariable Long id) {
        try {
            var city = cityService.getCityById(id);
            return ResponseEntity.ok(new ApiResponse<>("Get city success", city));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }


    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<CityDTO>>> getMethodName() {
        try {
            List<CityDTO> cities = cityService.getCityes();
            return ResponseEntity.ok(new ApiResponse<>("Success", cities));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>("Error!", null));
        }
    }
    

    @GetMapping("/city/by/name")
    public ResponseEntity<ApiResponse<CityDTO>> getMethodName(@RequestParam String name) {
        try {
            CityDTO city = cityService.getCityByName(name.replaceFirst(".",  String.valueOf(name.charAt(0)).toUpperCase()));
            return ResponseEntity.ok(new ApiResponse<>("Success", city));
        } catch (CityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }
    

    
    @PutMapping("city/{id}/update")
    public ResponseEntity<ApiResponse<CityDTO>> putMethodName(@PathVariable Long id,
            @RequestBody UpdateCityByName uppCityByName) {
        try {
            var city = cityService.updateCity(uppCityByName, id);
            return ResponseEntity.ok(new ApiResponse<>("Update city success", city));
        } catch (CityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }
    
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse<CityDTO>> deleteCity(@PathVariable Long id) {
        try {
            cityService.deleteCityById(id);
            return ResponseEntity.ok(new ApiResponse<>("Delete city success", cityService.getCityById(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    
}
