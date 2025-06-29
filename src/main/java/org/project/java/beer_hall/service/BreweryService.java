package org.project.java.beer_hall.service;

import java.util.List;
import java.util.Optional;

import org.project.java.beer_hall.model.Beer;
import org.project.java.beer_hall.model.Brewery;
import org.project.java.beer_hall.repository.BreweryRepository;
import org.project.java.beer_hall.specification.BrewerySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BreweryService {

    @Autowired
    private BreweryRepository breweryRepository;

    public List<Brewery> findAll() {
        return breweryRepository.findAll();
    }

    public List<Brewery> findAllByParams(String name, String nation) {
        return breweryRepository.findAll(BrewerySpecification.filter(name, nation));
    }

    public Optional<Brewery> findById(Integer id) {
        return breweryRepository.findById(id);
    }

    public Brewery getById(Integer id) {
        Optional<Brewery> brewery = breweryRepository.findById(id);

        if (brewery.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return brewery.get();
    }

    public Brewery create(Brewery brewery) {
        return breweryRepository.save(brewery);
    }

    public Brewery update(Brewery brewery) {
        return breweryRepository.save(brewery);
    }

    public void delete(Brewery brewery) {
        if (!brewery.getBeers().isEmpty()) {
            for (Beer beer : brewery.getBeers()) {
                beer.setBrewery(null);
            }
        }

        breweryRepository.delete(brewery);
    }

    public void deleteById(Integer id) {
        Brewery brewery = getById(id);

        delete(brewery);
    }

    public Boolean existsById(Integer id) {
        return breweryRepository.existsById(id);
    }

    public Boolean exists(Brewery brewery) {
        return breweryRepository.existsById(brewery.getId());
    }
}
