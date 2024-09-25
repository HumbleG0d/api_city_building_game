package com.buil.api.request;

import java.math.BigDecimal;
import java.util.List;

import com.buil.api.model.Population;
import com.buil.api.model.resource.Resource;

import lombok.Data;

@Data
public class UpdateCityRequest {
    private Long id;
    private String name;
    private List<Resource> resources;
    private Population population;
    private BigDecimal coins; 
}
