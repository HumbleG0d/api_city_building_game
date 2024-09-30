package com.buil.api.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Data Transfer Object (DTO) for transferring City data between processes.
 * This DTO includes information about the city's ID, name, resources, population, coins, and infrastructures.
 */
public class CityDTO {
    
    // Unique identifier for the city
    private Long id;

    // Name of the city
    private String name;

    // List of resources available in the city
    private List<ResourceDTO> resource;

    // Total population in the city
    private int totalPopulation;

    // Total amount of coins the city possesses (economic balance)
    private BigDecimal coins;

    // List of infrastructures present in the city
    private List<InfraestructureDTO> infraestructures;

}
