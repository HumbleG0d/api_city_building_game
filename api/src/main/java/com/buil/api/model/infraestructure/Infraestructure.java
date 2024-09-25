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
public class Infraestructure {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @ElementCollection
  @CollectionTable(name = "infraestructure_cost", joinColumns = @JoinColumn(name = "infraestructure_id"))
  @MapKeyColumn(name = "resource")
  @Column(name = "cost")
  private Map<String, Integer> cost;

  @Column(nullable = false)
  private int timeConstruction;

  @Enumerated(EnumType.STRING)
  private TypeInfraestructure typeInfraestructure;

  @Column(nullable = false)
  private int ability;

  @Column(length = 500)
  private String description;

  public Infraestructure(String name, TypeInfraestructure typeInfraestructure,Map<String, Integer> cost, int timeConstruction,  int ability, String description) {
    this.name = name;
    this.cost = cost;
    this.timeConstruction = timeConstruction;
    this.typeInfraestructure = typeInfraestructure;
    this.ability = ability;
    this.description = description;
  }

  @Column(nullable = false)
  private boolean constructionCompleted;

  @ManyToOne
  @JoinColumn(name = "primary_resource_id")
  private Resource mainResource;

  @ManyToOne
  @JoinColumn(name = "city_id", nullable = false)
  private City city;
}
