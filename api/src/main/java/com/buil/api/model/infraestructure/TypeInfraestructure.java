package com.buil.api.model.infraestructure;

public enum TypeInfraestructure {
    RANCH,
    BUILDING,
    FACTORY;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static TypeInfraestructure fromString(String value) {
        return TypeInfraestructure.valueOf(value.toUpperCase()); 
    }
}
