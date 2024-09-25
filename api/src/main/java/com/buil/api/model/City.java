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
public class City {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  @OneToMany(mappedBy = "city")
  private List<Resource> resources;
  @OneToOne(mappedBy = "city", cascade = CascadeType.ALL)
  private Population population;

  private BigDecimal coins;

  @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
  private List<Infraestructure> infraestructure;


  public City(String name , List<Resource> resources , Population population) {
    this.name = name;
    this.resources = resources;
    this.population = population;
    coins = new BigDecimal(1000);
  }
}
