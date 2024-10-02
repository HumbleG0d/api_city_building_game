package com.buil.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import com.buil.api.model.infraestructure.Infraestructure;
import com.buil.api.model.resource.Resource;

@Entity(name = "city")
@Table(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
/**
 * Entity representing a city in the city-building game.
 * A city is composed of resources, population, infrastructure, and a certain amount of currency (coins).
 */
public class City {

    /**
     * Unique identifier for the city, generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The name of the city.
     */
    private String name;

    /**
     * List of resources associated with the city.
     * One city can have multiple resources.
     */
    @OneToMany(mappedBy = "city")
    private List<Resource> resources;

    /**
     * The population of the city. There is a one-to-one relationship between a city and its population.
     */
    @OneToOne(mappedBy = "city", cascade = CascadeType.ALL)
    private Population population;

    /**
     * The amount of coins a city has. This is the currency used for managing the city’s operations and growth.
     * Default value is set to 1000 coins.
     */
    private BigDecimal coins;

    /**
     * List of infrastructure objects in the city.
     * One city can have multiple infrastructures, such as buildings, ranches, and factories.
     */
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Infraestructure> infraestructure;

    /**
     * Constructor to create a city with a name, resources, and population.
     * Coins are initialized with a default value of 1000.
     *
     * @param name the name of the city
     * @param resources list of resources associated with the city
     * @param population the population associated with the city
     */
    public City(String name, List<Resource> resources, Population population) {
        this.name = name;
        this.resources = resources;
        this.population = population;
        this.coins = new BigDecimal(1000);
    }
}
