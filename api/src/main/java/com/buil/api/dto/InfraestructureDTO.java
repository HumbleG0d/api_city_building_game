package com.buil.api.dto;

import java.util.Map;

import com.buil.api.model.infraestructure.TypeInfraestructure;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InfraestructureDTO {
    private String nome;
    private TypeInfraestructure typeInfraestructure;
    private Map<String, Integer> cost;
    private int constructionTime;
    private int capacity;
}
