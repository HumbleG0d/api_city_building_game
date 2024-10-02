package com.buil.api.model.infraestructure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

import com.buil.api.model.City;
import com.buil.api.model.resource.Resource;

@Entity
@Table(name = "infraestructures")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

/**
 * Entity representing an infrastructure in the city-building game.
 * Infrastructure includes details such as cost, construction time, type, and ability,
 * and is associated with a city and a primary resource.
 */
public class Infraestructure {

  /**
   * Unique identifier for the infrastructure, generated automatically.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Name of the infrastructure (e.g., ranch, building, factory).
   */
  @Column(nullable = false)
  private String name;

  /**
   * Map representing the cost of the infrastructure in terms of resources.
   * The key is the resource name, and the value is the cost in units of that resource.
   */
  @ElementCollection
  @CollectionTable(name = "infraestructure_cost", joinColumns = @JoinColumn(name = "infraestructure_id"))
  @MapKeyColumn(name = "resource")
  @Column(name = "cost")
  private Map<String, Integer> cost;

  /**
   * Time required to construct the infrastructure, expressed in game units.
   */
  @Column(nullable = false)
  private int timeConstruction;

  /**
   * Type of infrastructure (e.g., ranch, building, factory).
   */
  @Enumerated(EnumType.STRING)
  private TypeInfraestructure typeInfraestructure;

  /**
   * The infrastructure’s ability, which represents its efficiency or production capacity.
   */
  @Column(nullable = false)
  private int ability;

  /**
   * Description providing more details about the infrastructure (optional, max 500 characters).
   */
  @Column(length = 500)
  private String description;

  /**
   * Boolean flag indicating whether construction of the infrastructure is complete.
   */
  @Column(nullable = false)
  private boolean constructionCompleted;

  /**
   * The primary resource used by this infrastructure.
   */
  @ManyToOne
  @JoinColumn(name = "primary_resource_id")
  private Resource mainResource;

  /**
   * The city to which this infrastructure belongs.
   * There is a many-to-one relationship between infrastructure and city.
   */
  @ManyToOne
  @JoinColumn(name = "city_id", nullable = false)
  private City city;

  /**
   * Constructor to create an infrastructure with a name, type, cost, construction time,
   * ability, description, and other properties.
   *
   * @param name the name of the infrastructure
   * @param typeInfraestructure the type of the infrastructure (e.g., ranch, building, factory)
   * @param cost the cost of the infrastructure in terms of resources
   * @param timeConstruction the time required to construct the infrastructure
   * @param ability the production or efficiency ability of the infrastructure
   * @param description additional description of the infrastructure
   */
  public Infraestructure(String name, TypeInfraestructure typeInfraestructure, Map<String, Integer> cost, int timeConstruction, int ability, String description) {
      this.name = name;
      this.cost = cost;
      this.timeConstruction = timeConstruction;
      this.typeInfraestructure = typeInfraestructure;
      this.ability = ability;
      this.description = description;
      this.constructionCompleted = false;
  }
}