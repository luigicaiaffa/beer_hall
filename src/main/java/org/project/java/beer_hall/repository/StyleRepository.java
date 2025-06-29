package org.project.java.beer_hall.repository;

import java.util.List;

import org.project.java.beer_hall.model.Style;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StyleRepository extends JpaRepository<Style, Integer> {
    
    public List<Style> findByNameContainingIgnoreCase(String name);
}
