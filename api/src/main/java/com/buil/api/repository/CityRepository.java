package com.buil.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.buil.api.model.City;

public interface CityRepository extends JpaRepository<City,Long> {
    City findByName(String name);
}
