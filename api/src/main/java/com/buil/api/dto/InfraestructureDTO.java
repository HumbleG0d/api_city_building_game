package com.buil.api.dto;

import java.util.Map;

import com.buil.api.model.infraestructure.TypeInfraestructure;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * Data Transfer Object (DTO) for transferring Infrastructure data between processes.
 * This DTO includes information about the infrastructure's name, type, cost, construction time, and capacity.
 */
public class InfraestructureDTO {
    
    // Name of the infrastructure (e.g., Farm, Factory)
    private String name;

    // Type of the infrastructure, represented by an enum (e.g., RANCH, BUILDING, FACTORY)
    private TypeInfraestructure typeInfraestructure;

    // Map representing the cost of the infrastructure, with resource types as keys and their required amount as values
    private Map<String, Integer> cost;

    // Time required to construct the infrastructure, in days or game units
    private int constructionTime;

    // Capacity of the infrastructure, representing the number of people or amount of resources it can handle
    private int capacity;

    // Getters and setters omitted for brevity
}
