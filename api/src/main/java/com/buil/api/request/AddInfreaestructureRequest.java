package com.buil.api.request;

import java.util.Map;

import com.buil.api.model.infraestructure.TypeInfraestructure;

import lombok.Data;

@Data
public class AddInfreaestructureRequest {
    private Long id;
    private String name;
    private TypeInfraestructure typeInfraestructure;
    private Map<String, Integer> cost;
    private int timeConstruction;
    private int ability;
    private String description;
}
