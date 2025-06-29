package org.project.java.beer_hall.repository;

import java.util.List;

import org.project.java.beer_hall.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BeerRepository extends JpaRepository<Beer, Integer>, JpaSpecificationExecutor<Beer> {

    public List<Beer> findByNameContainingIgnoreCaseAndBreweryNationNameContainingIgnoreCaseAndStyleNameContainingIgnoreCaseAndBreweryNameContainingIgnoreCase(
            String name, String nation, String style, String brewery);

}
