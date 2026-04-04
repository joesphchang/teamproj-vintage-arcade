package com.vintagearcade.entity;

import com.vintagearcade.entity.Cabinet;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The type User.
 */
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String username;
    private String email;
    private String password; // store hashed passwords

    @ManyToMany
    @JoinTable(
            name = "UserFavorites",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "gameId")
    )
    private Set<Cabinet> favoriteCabinets = new HashSet<>();

    /**
     * Instantiates a new User.
     */
    public User() {}

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param email    the email
     * @param password the password
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
// getters and setters
    public int getUserId() { return userId; }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) { this.userId = userId; }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() { return email; }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() { return password; }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Gets favorite cabinets.
     *
     * @return the favorite cabinets
     */
    public Set<Cabinet> getFavoriteCabinets() { return favoriteCabinets; }

    /**
     * Sets favorite cabinets.
     *
     * @param favoriteCabinets the favorite cabinets
     */
    public void setFavoriteCabinets(Set<Cabinet> favoriteCabinets) { this.favoriteCabinets = favoriteCabinets; }
}