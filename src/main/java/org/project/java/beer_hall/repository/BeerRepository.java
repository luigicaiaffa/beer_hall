package org.project.java.beer_hall.repository;

import java.util.List;

import org.project.java.beer_hall.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BeerRepository extends JpaRepository<Beer, Integer> {

    public List<Beer> findByNameContainingIgnoreCase(String beerName);

    public List<Beer> findByBreweryNationName(String nationName);

    public List<Beer> findByStyleName(String styleName);

    public List<Beer> findByBreweryName(String breweryName);

    public List<Beer> findByNameContainingIgnoreCaseAndBreweryNationNameContainingIgnoreCaseAndStyleNameContainingIgnoreCaseAndBreweryNameContainingIgnoreCase(
            String name, String nation, String style, String brewery);

}
