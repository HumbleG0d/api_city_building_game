package com.buil.api.request;

import lombok.Data;

@Data
/**
 * Request object for updating a city by its name.
 * This class contains the necessary data to identify and modify a city's details.
 */
public class UpdateCityByName {

    // Unique identifier for the city
    private Long id;

    // New name for the city
    private String name;
}
