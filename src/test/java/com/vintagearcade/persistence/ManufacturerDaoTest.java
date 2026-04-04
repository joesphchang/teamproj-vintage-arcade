package com.vintagearcade.persistence;

import com.vintagearcade.entity.Manufacturer;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ManufacturerDaoTest {

    private GenericDao<Manufacturer> manufacturerDao;

    @BeforeAll
    void setup() {
        manufacturerDao = new GenericDao<>(Manufacturer.class);
    }

    @Test
    void testInsertAndGetById() {
        Manufacturer m = new Manufacturer("Namco", "Japan", 1955);
        manufacturerDao.insert(m);
        int id = m.getManufacturerId();
        assertTrue(id > 0);

        Manufacturer retrieved = manufacturerDao.getById(id);
        assertEquals("Namco", retrieved.getName());
    }

    @Test
    void testUpdate() {
        Manufacturer m = manufacturerDao.getAll().get(0);
        m.setCountry("USA");
        manufacturerDao.update(m);

        Manufacturer updated = manufacturerDao.getById(m.getManufacturerId());
        assertEquals("USA", updated.getCountry());
    }

    @Test
    void testDelete() {
        Manufacturer m = manufacturerDao.getAll().get(0);
        manufacturerDao.delete(m);

        Manufacturer deleted = manufacturerDao.getById(m.getManufacturerId());
        assertNull(deleted);
    }

    @Test
    void testGetAll() {
        List<Manufacturer> manufacturers = manufacturerDao.getAll();
        assertNotNull(manufacturers);
    }

    @Test
    void testGetByPropertyEqual() {
        Manufacturer m = new Manufacturer("Equal Test", "Germany", 1980);
        manufacturerDao.insert(m);

        List<Manufacturer> results = manufacturerDao.getByPropertyEqual("name", "Equal Test");
        assertFalse(results.isEmpty());
    }

    @Test
    void testGetByPropertyLike() {
        Manufacturer m = new Manufacturer("Like Test Corp", "France", 1990);
        manufacturerDao.insert(m);

        List<Manufacturer> results = manufacturerDao.getByPropertyLike("name", "Like Test");
        assertFalse(results.isEmpty());
    }
}