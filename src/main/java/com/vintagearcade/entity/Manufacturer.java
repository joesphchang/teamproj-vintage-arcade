package com.vintagearcade.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Manufacturer.
 */
@Entity
@Table(name = "Manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int manufacturerId;

    private String name;

    private String country;

    private int foundedYear;

    @OneToMany(mappedBy = "manufacturer")
    private Set<Cabinet> cabinets = new HashSet<>();

    /**
     * Instantiates a new Manufacturer.
     */
    public Manufacturer() {}

    public Manufacturer(String name, String country, int foundedYear) {
        this.name = name;
        this.country = country;
        this.foundedYear = foundedYear;
    }

    // getters and setters
    /**
     * Gets manufacturer id.
     *
     * @return the manufacturer id
     */
    public int getManufacturerId() {
        return manufacturerId;
    }

    /**
     * Sets manufacturer id.
     *
     * @param manufacturerId the manufacturer id
     */
    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets cabinets.
     *
     * @return the cabinets
     */
    public Set<Cabinet> getCabinets() {
        return cabinets;
    }

    /**
     * Sets cabinets.
     *
     * @param cabinets the cabinets
     */
    public void setCabinets(Set<Cabinet> cabinets) {
        this.cabinets = cabinets;
    }

    /**
     * Gets founded year.
     *
     * @return the founded year
     */
    public int getFoundedYear() {
        return foundedYear;
    }

    /**
     * Sets founded year.
     *
     * @param foundedYear the founded year
     */
    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }
}