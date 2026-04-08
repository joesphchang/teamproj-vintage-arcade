package com.vintagearcade.persistence;

import com.vintagearcade.entity.Cabinet;
import com.vintagearcade.entity.GameCondition;
import com.vintagearcade.entity.Manufacturer;
import com.vintagearcade.entity.Venue;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Venue dao test.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VenueDaoTest {

    private VenueDao venueDao;
    private CabinetDao cabinetDao;
    private GenericDao<GameCondition> conditionDao;
    private GenericDao<Manufacturer> manufacturerDao;

    @BeforeAll
    void setup() {
        cabinetDao = new CabinetDao();
        venueDao = new VenueDao();
        manufacturerDao = new GenericDao<>(Manufacturer.class);
        conditionDao = new GenericDao<>(GameCondition.class);
    }

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

        Cabinet cabinet = new Cabinet("Venue Test Game", 1995, 0.25, manufacturer, condition, null);
        cabinetDao.insert(cabinet);
        return cabinet;
    }

    @Test
    void testInsertAndGetById() {

        Venue venue = new Venue("Retro Arcade", "123 Main St");
        venueDao.insert(venue);

        Venue retrieved = venueDao.getById(venue.getVenueId());

        assertEquals("Retro Arcade", retrieved.getName());
    }

    @Test
    void testUpdate() {

        Venue venue = new Venue("Update Test", "Old Address");
        venueDao.insert(venue);

        venue.setLocation("New Address");
        venueDao.update(venue);

        Venue updated = venueDao.getById(venue.getVenueId());

        assertEquals("New Address", updated.getLocation());
    }

    @Test
    void testDelete() {

        Venue venue = new Venue("Delete Test", "Test St");
        venueDao.insert(venue);

        venueDao.delete(venue);

        Venue deleted = venueDao.getById(venue.getVenueId());

        assertNull(deleted);
    }

    @Test
    void testGetAll() {

        venueDao.insert(new Venue("Arcade 1", "A St"));

        List<Venue> venues = venueDao.getAll();

        assertFalse(venues.isEmpty());
    }

    @Test
    void testGetByPropertyEqual() {

        Venue venue = new Venue("Equal Test", "789 Oak St");
        venueDao.insert(venue);

        List<Venue> results = venueDao.getByPropertyEqual("name", "Equal Test");

        assertFalse(results.isEmpty());
    }

    @Test
    void testGetByPropertyLike() {

        Venue venue = new Venue("Like Test Arcade", "101 Pine St");
        venueDao.insert(venue);

        List<Venue> results = venueDao.getByPropertyLike("name", "Like Test");

        assertFalse(results.isEmpty());
    }

    @Test
    void testDeleteVenueRemovesCabinetRelationship() {

        Venue venue = new Venue("Delete Test Arcade", "Test St");
        venueDao.insert(venue);

        Cabinet cabinet = createTestCabinet();

        venue.getCabinets().add(cabinet);
        venueDao.update(venue);

        int venueId = venue.getVenueId();

        venueDao.delete(venue);

        Venue deletedVenue = venueDao.getById(venueId);

        assertNull(deletedVenue);
    }

    @Test
    public void testDeleteVenueDoesNotDeleteCabinets() {
        Venue venue = new Venue("Test Venue", "Addr");
        venueDao.insert(venue);

        Cabinet cabinet = createTestCabinet();

        // Link both ways
        venue.getCabinets().add(cabinet);
        cabinet.getVenues().add(venue);

        venueDao.update(venue);
        cabinetDao.updateCabinet(cabinet);  // use updateCabinet to ensure validation

        int cabinetId = cabinet.getGameId();

        venueDao.deleteVenue(venue.getVenueId());

        // Cabinet should still exist
        Cabinet stillExists = cabinetDao.getById(cabinetId);
        assertNotNull(stillExists);

    }
}