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
public class Population {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private int total_inhabitants;
  private double growth;
  private double pbi;
  @OneToOne
  @JoinColumn(name = "city_id")
  private City city;

  public Population(int total_inhabitants, double growth, double pbi) {
    this.total_inhabitants = total_inhabitants;
    this.growth = growth;
    this.pbi = pbi;
  }
}
