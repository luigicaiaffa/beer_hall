package org.project.java.beer_hall.repository;

import org.project.java.beer_hall.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Integer> {

}
