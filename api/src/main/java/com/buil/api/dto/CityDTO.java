package com.buil.api.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {
    private Long id;
    private String name;
    private List<ResourceDTO> resource;
    private int totalPopulation;
    private BigDecimal coins;
    private List<InfraestructureDTO> infraestructures;
}
