package org.project.java.beer_hall.service;

import java.util.List;
import java.util.Optional;

import org.project.java.beer_hall.model.Beer;
import org.project.java.beer_hall.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BeerService {

    @Autowired
    private BeerRepository beerRepository;

    public List<Beer> findAll() {
        return beerRepository.findAll();
    }

    public Optional<Beer> findById(Integer id) {
        return beerRepository.findById(id);
    }

    public Beer getById(Integer id) {
        Optional<Beer> beer = beerRepository.findById(id);

        if (beer.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return beer.get();
    }

    public List<Beer> findByName(String name) {
        return beerRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Beer> findAllByAllParams(String name, String nation, String style, String brewery) {
        return beerRepository
                .findByNameContainingIgnoreCaseAndBreweryNationNameContainingIgnoreCaseAndStyleNameContainingIgnoreCaseAndBreweryNameContainingIgnoreCase(
                        name, nation, style, brewery);
    }

    public Beer create(Beer beer) {
        return beerRepository.save(beer);
    }

    public Beer update(Beer beer) {
        return beerRepository.save(beer);
    }

    public void delete(Beer beer) {
        beerRepository.delete(beer);
    }

    public void deleteById(Integer id) {
        Beer beer = getById(id);

        beerRepository.delete(beer);
    }

    public Boolean existsById(Integer id) {
        return beerRepository.existsById(id);
    }

    public Boolean exists(Beer beer) {
        return beerRepository.existsById(beer.getId());
    }

}
