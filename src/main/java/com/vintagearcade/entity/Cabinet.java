package com.vintagearcade.entity;

import jakarta.persistence.*;

import java.util.Set;

/**
 * The type Cabinet.
 */
@Entity
@Table(name = "Cabinet")
public class Cabinet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameId;

    private String gameName;

    private int year;

    private double pricePerPlay;

    @ManyToOne
    @JoinColumn(name = "manufacturerId")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "conditionId")
    private GameCondition condition;

    @ManyToMany(mappedBy = "cabinets")
    private Set<Venue> venues;

    /**
     * Instantiates a new Cabinet.
     */
    public Cabinet() {}

    public Cabinet(String gameName, int year, double pricePerPlay, Manufacturer manufacturer, GameCondition condition, Set<Venue> venues) {
        this.gameName = gameName;
        this.year = year;
        this.pricePerPlay = pricePerPlay;
        this.manufacturer = manufacturer;
        this.condition = condition;
        this.venues = venues;
    }

    // getters and setters

    /**
     * Gets game id.
     *
     * @return the game id
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * Sets game id.
     *
     * @param gameId the game id
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * Gets game name.
     *
     * @return the game name
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * Sets game name.
     *
     * @param gameName the game name
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets price per play.
     *
     * @return the price per play
     */
    public double getPricePerPlay() {
        return pricePerPlay;
    }

    /**
     * Sets price per play.
     *
     * @param pricePerPlay the price per play
     */
    public void setPricePerPlay(double pricePerPlay) {
        this.pricePerPlay = pricePerPlay;
    }

    /**
     * Gets manufacturer.
     *
     * @return the manufacturer
     */
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets manufacturer.
     *
     * @param manufacturer the manufacturer
     */
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Gets condition.
     *
     * @return the condition
     */
    public GameCondition getCondition() {
        return condition;
    }

    /**
     * Sets condition.
     *
     * @param condition the condition
     */
    public void setCondition(GameCondition condition) {
        this.condition = condition;
    }

    /**
     * Gets venues.
     *
     * @return the venues
     */
    public Set<Venue> getVenues() {
        return venues;
    }

    /**
     * Sets venues.
     *
     * @param venues the venues
     */
    public void setVenues(Set<Venue> venues) {
        this.venues = venues;
    }
}