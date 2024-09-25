package com.buil.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buil.api.dto.CityDTO;
import com.buil.api.request.AddCityRequest;
import com.buil.api.response.ApiResponse;
import com.buil.api.services.city.CityService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



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
    
    
}
