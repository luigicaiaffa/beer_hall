package org.project.java.beer_hall.repository;

import org.project.java.beer_hall.model.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BreweryRepository extends JpaRepository<Brewery, Integer>, JpaSpecificationExecutor<Brewery> {

}
