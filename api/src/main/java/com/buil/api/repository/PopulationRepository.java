package com.buil.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buil.api.model.Population;

public interface PopulationRepository extends JpaRepository<Population, Long> {
    Population findByCityName(String nameCity);
}
