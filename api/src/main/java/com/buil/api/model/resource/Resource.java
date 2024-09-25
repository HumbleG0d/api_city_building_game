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
public class Resource {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Enumerated(EnumType.STRING)
  private Type_resource typeResource;

  private String name;
  private int total;

  @ManyToOne
  @JoinColumn(name = "city_id", nullable = false)
  private City city;

  public Resource(String name ,Type_resource typeResource, int total, City city) {
    this.name = name;
    this.typeResource = typeResource;
    this.total = total;
    this.city = city;
  }
}
