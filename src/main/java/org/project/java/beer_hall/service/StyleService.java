package org.project.java.beer_hall.service;

import java.util.List;
import java.util.Optional;

import org.project.java.beer_hall.model.Beer;
import org.project.java.beer_hall.model.Style;
import org.project.java.beer_hall.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StyleService {

    @Autowired
    private StyleRepository styleRepository;

    public List<Style> findAll() {
        return styleRepository.findAll();
    }

    public List<Style> findByName(String name) {
        return styleRepository.findByNameContainingIgnoreCase(name);
    }

    public Optional<Style> findById(Integer id) {
        return styleRepository.findById(id);
    }

    public Style getById(Integer id) {
        Optional<Style> style = styleRepository.findById(id);

        if (style.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return style.get();
    }

    public Style create(Style style) {
        return styleRepository.save(style);
    }

    public Style update(Style style) {
        return styleRepository.save(style);
    }

    public void delete(Style style) {
        styleRepository.delete(style);
    }

    public void deleteById(Integer id) {
        Style style = getById(id);

        if (style.getBeers().isEmpty()) {
            for (Beer beer : style.getBeers()) {
                beer.setStyle(null);
            }
        }

        delete(style);
    }

    public Boolean existsById(Integer id) {
        return styleRepository.existsById(id);
    }

    public Boolean exists(Style style) {
        return styleRepository.existsById(style.getId());
    }
}
