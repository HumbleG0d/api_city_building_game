package com.buil.api.request;

import lombok.Data;


/**
 * Represents a request to update the population details of a city.
 * This class encapsulates the data required to modify the attributes 
 * related to the city's population.
 */
@Data
public class UpdatePopulationRequest {

    // Total number of inhabitants in the city
    private int total_inhabitants;
    
    // Growth rate of the population
    private double growth;
    
    // Productive output of the city (PBI - Producto Bruto Interno)
    private double pbi;

    /**
     * Constructs a new UpdatePopulationRequest with the specified values.
     *
     * @param total_inhabitants the total number of inhabitants
     * @param growth the growth rate of the population
     * @param pbi the productive output of the city
     */
    public UpdatePopulationRequest(int total_inhabitants, double growth, double pbi) {
        this.total_inhabitants = total_inhabitants;
        this.growth = growth;
        this.pbi = pbi;
    }
}