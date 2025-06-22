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
@Table(name = "nations")
public class Nation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @OneToMany(mappedBy = "nation")
    private List<Brewery> breweries;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Brewery> getBreweries() {
        return breweries;
    }

    public void setBreweries(List<Brewery> breweries) {
        this.breweries = breweries;
    }

    public Nation() {
    }

    public Nation(@NotBlank String name, @NotBlank String code, List<Brewery> breweries) {
        this.name = name;
        this.code = code;
        this.breweries = breweries;
    }

    @Override
    public String toString() {
        return this.code;
    }

}
