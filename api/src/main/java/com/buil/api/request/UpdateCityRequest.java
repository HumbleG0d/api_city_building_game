package com.buil.api.request;

import java.math.BigDecimal;
import java.util.List;

import com.buil.api.model.Population;
import com.buil.api.model.resource.Resource;

import lombok.Data;

/**
 * Represents a request to update the details of a city.
 * This class is used to transfer the necessary information 
 * required to modify a city's attributes.
 */
@Data
public class UpdateCityRequest {
    
    // Unique identifier for the city
    private Long id;
    
    // New name for the city
    private String name;
    
    // List of resources associated with the city
    private List<Resource> resources;
    
    // Population details of the city
    private Population population;
    
    // Amount of coins available in the city
    private BigDecimal coins; 
}
