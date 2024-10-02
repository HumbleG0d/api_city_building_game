package com.buil.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "population")
@Table(name = "population")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
/**
 * Entity representing the population of a city in the city-building game.
 * The population is tied to a city and includes details about the total number of inhabitants,
 * the growth rate, and the city's PBI (Gross Domestic Product).
 */
public class Population {

  /**
   * Unique identifier for the population, generated automatically.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  /**
   * Total number of inhabitants in the city.
   */
  private int total_inhabitants;

  /**
   * Growth rate of the city's population, expressed as a percentage.
   */
  private double growth;

  /**
   * The Gross Domestic Product (PBI) of the city.
   */
  private double pbi;

  /**
   * The city to which this population belongs.
   * There is a one-to-one relationship between a population and a city.
   */
  @OneToOne
  @JoinColumn(name = "city_id")
  private City city;

  /**
   * Constructor to create a population with total inhabitants, growth rate, and PBI.
   *
   * @param total_inhabitants the total number of people living in the city
   * @param growth the growth rate of the population
   * @param pbi the Gross Domestic Product of the city
   */
  public Population(int total_inhabitants, double growth, double pbi) {
      this.total_inhabitants = total_inhabitants;
      this.growth = growth;
      this.pbi = pbi;
  }
}
