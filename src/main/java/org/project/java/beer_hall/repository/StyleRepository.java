package org.project.java.beer_hall.repository;

import org.project.java.beer_hall.model.Style;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StyleRepository extends JpaRepository<Style, Integer> {
    
}
