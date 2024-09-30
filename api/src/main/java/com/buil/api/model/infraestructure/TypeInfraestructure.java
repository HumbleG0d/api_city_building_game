package com.buil.api.model.infraestructure;

/**
 * Enum representing the different types of infrastructure available in the city-building game.
 * Infrastructures are key components for resource management and city growth.
 */
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
