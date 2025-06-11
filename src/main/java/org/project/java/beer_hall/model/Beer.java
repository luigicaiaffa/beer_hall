package org.project.java.beer_hall.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "beers")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String size;

    @NotNull
    @PositiveOrZero
    private double alcoholDegrees;

    @NotBlank
    private String imgUrl;

    @OneToMany(mappedBy = "beer")
    private List<Brewery> breweries;

    @OneToMany(mappedBy = "beer")
    private List<Style> styles;

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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getAlcoholDegrees() {
        return alcoholDegrees;
    }

    public void setAlcoholDegrees(double alcoholDegrees) {
        this.alcoholDegrees = alcoholDegrees;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<Brewery> getBreweries() {
        return breweries;
    }

    public void setBreweries(List<Brewery> breweries) {
        this.breweries = breweries;
    }

    public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

    // # Constructors

    public Beer() {
    }

    public Beer(@NotBlank String name, @NotBlank String size, @NotNull @PositiveOrZero double alcoholDegrees,
            @NotBlank String imgUrl,
            List<Brewery> breweries, List<Style> styles) {
        this.name = name;
        this.size = size;
        this.alcoholDegrees = alcoholDegrees;
        this.imgUrl = imgUrl;
        this.breweries = breweries;
        this.styles = styles;
    }

    // # Methods

    @Override
    public String toString() {
        return this.name;
    }
}
