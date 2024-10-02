package com.buil.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.buil.api.model.City;
import com.buil.api.model.resource.Resource;
import com.buil.api.model.resource.Type_resource;


/**
 * Repository interface for managing {@link Resource} entities.
 * Extends JpaRepository to provide CRUD operations and custom queries for Resource entities.
 */
public interface ResourceRepository extends JpaRepository<Resource, Long> {

  /**
   * Retrieves a list of resources associated with a specific city by the city's ID.
   *
   * @param cityId the ID of the city whose resources are to be found
   * @return a list of {@link Resource} entities associated with the specified city
   */
  List<Resource> findByCityId(Long cityId);

  /**
   * Retrieves the total amount of all resources for a specific city by summing the `total` field.
   *
   * @param cityId the ID of the city whose total resource amount is to be calculated
   * @return the total sum of resources for the specified city
   */
  @Query("SELECT SUM(r.total) FROM resource r WHERE r.city.id = :cityId")
  int getTotalResourceByCity(@Param("cityId") Long cityId);

  /**
   * Retrieves a specific resource by the city and resource type.
   *
   * @param city the {@link City} entity associated with the resource
   * @param typeResource the type of the resource (e.g., NATURAL, ENERGY, AGRICULTURAL)
   * @return the {@link Resource} matching the specified city and type, or null if no resource is found
   */
  Resource findByCityAndTypeResource(City city, Type_resource typeResource);
}
