package com.buil.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.buil.api.model.City;
import com.buil.api.model.resource.Resource;
import com.buil.api.model.resource.Type_resource;

public interface ResourceRepository extends JpaRepository<Resource , Long> {
  List<Resource> findByCityId(Long cityId);
  //SELECT SUM(r.total) FROM resource r WHERE r.city.id = :cityId
  @Query("SELECT SUM(r.total) FROM resource r WHERE r.city.id = :cityId")
  int getTotalResourceByCity(@Param("cityId") Long cityId);
  Resource findByCityAndTypeResource(City city , Type_resource tiResource);
}
