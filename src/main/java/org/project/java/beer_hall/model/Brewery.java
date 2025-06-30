package org.project.java.beer_hall.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "breweries")
public class Brewery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "brewery")
    private List<Beer> beers;

    @NotNull
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "nation_id", nullable = false)
    private Nation nation;

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

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
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

    public Brewery(@NotBlank String name, @NotNull Nation nation, List<Beer> beers) {
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
