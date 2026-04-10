package com.vintagearcade.persistence;

import com.vintagearcade.entity.Manufacturer;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Manufacturer dao test.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ManufacturerDaoTest {

    private GenericDao<Manufacturer> manufacturerDao;

    /**
     * Sets .
     */
    @BeforeAll
    void setup() {
        manufacturerDao = new GenericDao<>(Manufacturer.class);
    }

    /**
     * Test insert and get by id.
     */
    @Test
    void testInsertAndGetById() {
        Manufacturer m = new Manufacturer("Namco", "Japan", 1955);
        manufacturerDao.insert(m);
        int id = m.getManufacturerId();
        assertTrue(id > 0);

        Manufacturer retrieved = manufacturerDao.getById(id);
        assertEquals("Namco", retrieved.getName());
    }

    /**
     * Test update.
     */
    @Test
    void testUpdate() {
        Manufacturer m = manufacturerDao.getAll().get(0);
        m.setCountry("USA");
        manufacturerDao.update(m);

        Manufacturer updated = manufacturerDao.getById(m.getManufacturerId());
        assertEquals("USA", updated.getCountry());
    }

    /**
     * Test delete.
     */
    @Test
    void testDelete() {
        Manufacturer m = manufacturerDao.getAll().get(0);
        manufacturerDao.delete(m);

        Manufacturer deleted = manufacturerDao.getById(m.getManufacturerId());
        assertNull(deleted);
    }

    /**
     * Test get all.
     */
    @Test
    void testGetAll() {
        List<Manufacturer> manufacturers = manufacturerDao.getAll();
        assertNotNull(manufacturers);
    }

    /**
     * Test get by property equal.
     */
    @Test
    void testGetByPropertyEqual() {
        Manufacturer m = new Manufacturer("Equal Test", "Germany", 1980);
        manufacturerDao.insert(m);

        List<Manufacturer> results = manufacturerDao.getByPropertyEqual("name", "Equal Test");
        assertFalse(results.isEmpty());
    }

    /**
     * Test get by property like.
     */
    @Test
    void testGetByPropertyLike() {
        Manufacturer m = new Manufacturer("Like Test Corp", "France", 1990);
        manufacturerDao.insert(m);

        List<Manufacturer> results = manufacturerDao.getByPropertyLike("name", "Like Test");
        assertFalse(results.isEmpty());
    }

    /**
     * Test get by invalid id.
     */
    @Test
    void testGetByInvalidId() {

        Manufacturer result = manufacturerDao.getById(-1);

        assertNull(result);
    }

    /**
     * Test get by property equal empty result.
     */
    @Test
    void testGetByPropertyEqualEmptyResult() {

        List<Manufacturer> results =
                manufacturerDao.getByPropertyEqual("name", "THIS_DOES_NOT_EXIST");

        assertTrue(results.isEmpty());
    }
}