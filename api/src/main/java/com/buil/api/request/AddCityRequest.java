package com.buil.api.request;

import java.math.BigDecimal;
import java.util.List;

import com.buil.api.model.Population;
import com.buil.api.model.resource.Resource;

import lombok.Data;

@Data
/**
 * Request object for adding a new City.
 * This class contains the necessary data to create or update a City entity.
 */
public class AddCityRequest {

    // Unique identifier for the city
    private Long id;

    // Name of the city to be added
    private String name;

    // List of resources available in the city (e.g., Wood, Stone, Energy)
    private List<Resource> resource;

    // Population details of the city, including total inhabitants and growth rates
    private Population population;

    // Amount of currency (moneda) available in the city
    private BigDecimal moneda;

    // Getters and setters omitted for brevity
}
