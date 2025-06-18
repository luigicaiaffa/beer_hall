package org.project.java.beer_hall.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "breweries")
public class Brewery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String nation;

    @OneToMany(mappedBy = "brewery")
    private List<Beer> beers;

    // # Getters / Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }

    // # Constructors

    public Brewery() {
    }

    public Brewery(@NotBlank String name, @NotBlank String nation, List<Beer> beers) {
        this.name = name;
        this.nation = nation;
        this.beers = beers;
    }

    // # Methods

    @Override
    public String toString() {
        return this.name;
    }
}
