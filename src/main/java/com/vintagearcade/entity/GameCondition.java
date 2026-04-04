package com.vintagearcade.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Game condition.
 */
@Entity
@Table(name = "GameCondition")
public class GameCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int conditionId;

    private String status;

    @OneToMany(mappedBy = "condition", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cabinet> cabinets =  new HashSet<>();

    /**
     * Instantiates a new Game condition.
     */
    public GameCondition() {}

    public GameCondition(String status, Set<Cabinet> cabinets) {
        this.status = status;
    }

    // getters and setters

    /**
     * Gets condition id.
     *
     * @return the condition id
     */
    public int getConditionId() {
        return conditionId;
    }

    /**
     * Sets condition id.
     *
     * @param conditionId the condition id
     */
    public void setConditionId(int conditionId) {
        this.conditionId = conditionId;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
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