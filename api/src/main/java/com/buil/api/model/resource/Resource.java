package com.buil.api.model.resource;

import com.buil.api.model.City;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "resource")
@Table(name = "resources")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
/**
 * Entity representing a resource in the city-building game.
 * A resource belongs to a city and has a type, name, and total amount available.
 */
public class Resource {

  /**
   * Unique identifier for the resource, generated automatically.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  /**
   * The type of the resource, which can be natural, energy, or agricultural.
   */
  @Enumerated(EnumType.STRING)
  private Type_resource typeResource;

  /**
   * The name of the resource.
   */
  private String name;

  /**
   * The total quantity of the resource available in the city.
   */
  private int total;

  /**
   * The city to which this resource belongs.
   * There is a many-to-one relationship between a resource and a city.
   */
  @ManyToOne
  @JoinColumn(name = "city_id", nullable = false)
  private City city;

  /**
   * Constructor to create a resource with a name, type, total amount, and associated city.
   *
   * @param name the name of the resource
   * @param typeResource the type of the resource (e.g., natural, energy, agricultural)
   * @param total the total quantity of the resource available
   * @param city the city to which this resource belongs
   */
  public Resource(String name, Type_resource typeResource, int total, City city) {
      this.name = name;
      this.typeResource = typeResource;
      this.total = total;
      this.city = city;
  }
}