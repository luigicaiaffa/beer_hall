package org.project.java.beer_hall.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "beers")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private Double size;

    @NotNull
    @PositiveOrZero
    private Double alcoholDegrees;

    private String imgUrl;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "brewery_id", nullable = true)
    private Brewery brewery;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "style_id", nullable = true)
    private Style style;

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

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getAlcoholDegrees() {
        return alcoholDegrees;
    }

    public void setAlcoholDegrees(Double alcoholDegrees) {
        this.alcoholDegrees = alcoholDegrees;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Brewery getBrewery() {
        return brewery;
    }

    public void setBrewery(Brewery brewery) {
        this.brewery = brewery;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    // # Constructors

    public Beer() {
    }

    public Beer(@NotBlank String name, @NotNull @Positive Double size,
            @NotNull @Positive Double alcoholDegrees, String imgUrl, Brewery brewery, Style style) {
        this.name = name;
        this.size = size;
        this.alcoholDegrees = alcoholDegrees;
        this.imgUrl = imgUrl;
        this.brewery = brewery;
        this.style = style;
    }

    // # Methods

    @Override
    public String toString() {
        return this.name;
    }
}
