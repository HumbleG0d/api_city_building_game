package com.buil.api.request;

import java.util.Map;

import com.buil.api.model.infraestructure.TypeInfraestructure;

import lombok.Data;

@Data
/**
 * Request object for adding a new Infrastructure.
 * This class contains the necessary data to create or update an Infrastructure entity.
 */
public class AddInfreaestructureRequest {

    // Unique identifier for the infrastructure
    private Long id;

    // Name of the infrastructure to be added
    private String name;

    // Type of the infrastructure (e.g., Factory, Building)
    private TypeInfraestructure typeInfraestructure;

    // Map representing the cost of the infrastructure with different cost categories
    private Map<String, Integer> cost;

    // Time required for construction (in hours/days)
    private int timeConstruction;

    // Ability or capacity of the infrastructure (e.g., number of inhabitants it can support)
    private int ability;

    // Description providing additional details about the infrastructure
    private String description;
}
