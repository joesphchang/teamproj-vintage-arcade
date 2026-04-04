package com.vintagearcade.persistence;

import com.vintagearcade.entity.Cabinet;
import com.vintagearcade.entity.Manufacturer;
import com.vintagearcade.entity.GameCondition;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Cabinet dao test.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CabinetDaoTest {

    private GenericDao<Cabinet> cabinetDao;

    /**
     * Sets .
     */
    @BeforeAll
    void setup() {
        cabinetDao = new GenericDao<>(Cabinet.class);
    }

    /**
     * Test insert and get by id.
     */
    @Test
    void testInsertAndGetById() {
        Manufacturer manufacturer = new Manufacturer("Namco", "Japan", 1955);
        GameCondition condition = new GameCondition("Good", null);

        Cabinet cabinet = new Cabinet("Pac-Man", 1980, 0.25, manufacturer, condition, null);

        cabinetDao.insert(cabinet);
        int id = cabinet.getGameId();
        assertTrue(id > 0);

        Cabinet retrieved = cabinetDao.getById(id);
        assertEquals("Pac-Man", retrieved.getGameName());
    }

    /**
     * Test update.
     */
    @Test
    void testUpdate() {
        Cabinet cabinet = cabinetDao.getAll().get(0);
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
        List<Cabinet> cabinets = cabinetDao.getAll();
        assertFalse(cabinets.isEmpty());
    }

    /**
     * Test delete.
     */
    @Test
    void testDelete() {
        Cabinet cabinet = cabinetDao.getAll().get(0);
        cabinetDao.delete(cabinet);

        Cabinet deleted = cabinetDao.getById(cabinet.getGameId());
        assertNull(deleted);
    }
}