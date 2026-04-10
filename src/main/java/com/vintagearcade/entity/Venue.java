package com.vintagearcade.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Venue.
 */
@Entity
@Table(
    name = "Venue",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "location"})}
)
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int venueId;

    private String name;
    private String location;

    private LocalTime openFrom;
    private LocalTime openTo;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "VenueCabinet",
            joinColumns = @JoinColumn(name = "venueId"),
            inverseJoinColumns = @JoinColumn(name = "gameId")
    )
    private Set<Cabinet> cabinets = new HashSet<>();

    /**
     * Instantiates a new Venue.
     */
    public Venue() {}


    /**
     * Instantiates a new Venue.
     *
     * @param name     the name
     * @param location the location
     */
    public Venue(String name, String location) {
        this.name = name;
        this.location = location;
        this.openFrom = LocalTime.now();
        this.openTo = LocalTime.now();
    }

    // getters and setters


    /**
     * Gets venue id.
     *
     * @return the venue id
     */
    public int getVenueId() {
        return venueId;
    }

    /**
     * Sets venue id.
     *
     * @param venueId the venue id
     */
    public void setVenueId(int venueId) {
        this.venueId = venueId;
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
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets open from.
     *
     * @return the open from
     */
    public LocalTime getOpenFrom() {
        return openFrom;
    }

    /**
     * Sets open from.
     *
     * @param openFrom the open from
     */
    public void setOpenFrom(LocalTime openFrom) {
        this.openFrom = openFrom;
    }

    /**
     * Gets open to.
     *
     * @return the open to
     */
    public LocalTime getOpenTo() {
        return openTo;
    }

    /**
     * Sets open to.
     *
     * @param openTo the open to
     */
    public void setOpenTo(LocalTime openTo) {
        this.openTo = openTo;
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
}