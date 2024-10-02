package com.buil.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * Data Transfer Object (DTO) for transferring Resource data between processes.
 * This DTO includes information about the resource's name and its total quantity.
 */
public class ResourceDTO {

    // Name of the resource (e.g., Wood, Stone, Energy)
    private String name;

    // Total amount of the resource available
    private int total;

}
