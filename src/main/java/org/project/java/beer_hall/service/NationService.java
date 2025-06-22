package org.project.java.beer_hall.service;

import java.util.List;

import org.project.java.beer_hall.model.Nation;
import org.project.java.beer_hall.repository.NationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NationService {

    @Autowired
    private NationRepository nationRepository;

    public List<Nation> findAll() {
        return nationRepository.findAll();
    }

}
