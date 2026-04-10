package com.vintagearcade.persistence;

import com.vintagearcade.entity.Cabinet;
import com.vintagearcade.entity.Manufacturer;
import com.vintagearcade.entity.GameCondition;
import com.vintagearcade.entity.Venue;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Cabinet dao test.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CabinetDaoTest {

    private CabinetDao cabinetDao;
    private VenueDao venueDao;
    private GenericDao<GameCondition> conditionDao;
    /**
     * The Manufacturer dao.
     */
    GenericDao<Manufacturer> manufacturerDao;


    /**
     * Sets .
     */
    @BeforeAll
    void setup() {
        cabinetDao = new CabinetDao();
        venueDao = new VenueDao();
        manufacturerDao = new GenericDao<>(Manufacturer.class);
        conditionDao = new GenericDao<>(GameCondition.class);
    }

    /**
     * Test insert and get by id.
     */
    private Manufacturer createTestManufacturer() {
        Manufacturer manufacturer = new Manufacturer("TestCo", "USA", 1990);
        manufacturerDao.insert(manufacturer);
        return manufacturer;
    }

    private GameCondition createTestCondition() {
        GameCondition condition = new GameCondition("Working", null);
        conditionDao.insert(condition);
        return condition;
    }

    private Cabinet createTestCabinet() {
        Manufacturer manufacturer = createTestManufacturer();
        GameCondition condition = createTestCondition();

        Cabinet cabinet = new Cabinet("Test Game", 1995, 0.25, manufacturer, condition, null);
        cabinetDao.insert(cabinet);
        return cabinet;
    }

    /**
     * Test insert and get by id.
     */
    @Test
    void testInsertAndGetById() {

        Cabinet cabinet = createTestCabinet();

        Cabinet retrieved = cabinetDao.getById(cabinet.getGameId());

        assertEquals("Test Game", retrieved.getGameName());
    }

    /**
     * Test update.
     */
    @Test
    void testUpdate() {

        Cabinet cabinet = createTestCabinet();

        cabinet.setPricePerPlay(0.5);
        cabinetDao.update(cabinet);

        Cabinet updated = cabinetDao.getById(cabinet.getGameId());

        assertEquals(0.5, updated.getPricePerPlay());
    }

    /**
     * Test get all.
     */
    @Test
    void testGetAll() {

        createTestCabinet();

        List<Cabinet> cabinets = cabinetDao.getAll();

        assertFalse(cabinets.isEmpty());
    }

    /**
     * Test delete.
     */
    @Test
    void testDelete() {

        Cabinet cabinet = createTestCabinet();

        cabinetDao.delete(cabinet);

        Cabinet deleted = cabinetDao.getById(cabinet.getGameId());

        assertNull(deleted);
    }

    /**
     * Test cabinet without venue.
     */
    @Test
    void testCabinetWithoutVenue() {

        Cabinet cabinet = createTestCabinet();

        Cabinet retrieved = cabinetDao.getById(cabinet.getGameId());

        assertNotNull(retrieved);
    }

    /**
     * Test update cabinet with invalid venue.
     */
    @Test
    void testUpdateCabinetWithInvalidVenue() {
        Cabinet cabinet = createTestCabinet();

        Venue fakeVenue = new Venue();
        fakeVenue.setVenueId(-999); // Non-existent
        cabinet.setVenues(Set.of(fakeVenue));

        assertThrows(IllegalArgumentException.class, () -> {
            cabinetDao.updateCabinet(cabinet);
        });
    }

    /**
     * Test delete cabinet with linked venues.
     */
    @Test
    void testDeleteCabinetWithLinkedVenues() {
        Cabinet cabinet = createTestCabinet();
        Venue venue = new Venue("Linked Venue", "123 Arcade St");
        venueDao.insert(venue);

        // Link both ways
        cabinet.setVenues(Set.of(venue));
        venue.getCabinets().add(cabinet);

        cabinetDao.updateCabinet(cabinet);
        venueDao.update(venue);

        int venueId = venue.getVenueId();
        int cabinetId = cabinet.getGameId();

        // Delete the cabinet
        cabinetDao.delete(cabinet);

        // Cabinet should be gone
        Cabinet deletedCabinet = cabinetDao.getById(cabinetId);
        assertNull(deletedCabinet);

        // Venue should still exist
        Venue stillExists = venueDao.getByIdWithCabinets(venueId);
        assertNotNull(stillExists);

        // Venue should no longer have the cabinet linked
        assertTrue(stillExists.getCabinets().isEmpty());
    }

    /**
     * Test update cabinet with null venues.
     */
    @Test
    void testUpdateCabinetWithNullVenues() {
        Cabinet cabinet = createTestCabinet();

        // Set venues to null
        cabinet.setVenues(null);
        cabinetDao.updateCabinet(cabinet);

        Cabinet updated = cabinetDao.getByIdWithVenues(cabinet.getGameId());
        assertNotNull(updated);
        assertTrue(updated.getVenues() == null || updated.getVenues().isEmpty());
    }

    /**
     * Test update cabinet with empty venues.
     */
    @Test
    void testUpdateCabinetWithEmptyVenues() {
        Cabinet cabinet = createTestCabinet();

        // Set venues to empty set
        cabinet.setVenues(Set.of());
        cabinetDao.updateCabinet(cabinet);

        Cabinet updated = cabinetDao.getByIdWithVenues(cabinet.getGameId());
        assertNotNull(updated);
        //assertEquals(updated.getCondition(), cabinet.getCondition());
        assertTrue(updated.getVenues() == null || updated.getVenues().isEmpty());
    }
}