package com.vintagearcade.persistence;

import com.vintagearcade.entity.Venue;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Venue dao test.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VenueDaoTest {

    private GenericDao<Venue> venueDao;

    /**
     * Sets .
     */
    @BeforeAll
    void setup() {
        venueDao = new GenericDao<>(Venue.class);
    }

    /**
     * Test insert and get by id.
     */
    @Test
    void testInsertAndGetById() {
        Venue venue = new Venue("Retro Arcade", "123 Main St");
        venueDao.insert(venue);
        int id = venue.getVenueId();
        assertTrue(id > 0);

        Venue retrieved = venueDao.getById(id);
        assertEquals("Retro Arcade", retrieved.getName());
    }

    /**
     * Test update.
     */
    @Test
    void testUpdate() {
        Venue venue = venueDao.getAll().get(0);
        venue.setLocation("456 Elm St");
        venueDao.update(venue);

        Venue updated = venueDao.getById(venue.getVenueId());
        assertEquals("456 Elm St", updated.getLocation());
    }

    /**
     * Test delete.
     */
    @Test
    void testDelete() {
        Venue venue = venueDao.getAll().get(0);
        venueDao.delete(venue);

        Venue deleted = venueDao.getById(venue.getVenueId());
        assertNull(deleted);
    }

    /**
     * Test get all.
     */
    @Test
    void testGetAll() {
        List<Venue> venues = venueDao.getAll();
        assertNotNull(venues);
    }

    /**
     * Test get by property equal.
     */
    @Test
    void testGetByPropertyEqual() {
        Venue venue = new Venue("Equal Test", "789 Oak St");
        venueDao.insert(venue);

        List<Venue> results = venueDao.getByPropertyEqual("name", "Equal Test");
        assertFalse(results.isEmpty());
    }

    /**
     * Test get by property like.
     */
    @Test
    void testGetByPropertyLike() {
        Venue venue = new Venue("Like Test Arcade", "101 Pine St");
        venueDao.insert(venue);

        List<Venue> results = venueDao.getByPropertyLike("name", "Like Test");
        assertFalse(results.isEmpty());
    }
}