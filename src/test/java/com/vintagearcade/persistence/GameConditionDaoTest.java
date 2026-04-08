package com.vintagearcade.persistence;

import com.vintagearcade.entity.GameCondition;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Game condition dao test.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameConditionDaoTest {

    private GenericDao<GameCondition> conditionDao;

    /**
     * Sets .
     */
    @BeforeAll
    void setup() {
        conditionDao = new GenericDao<>(GameCondition.class);
    }

    /**
     * Test insert and get by id.
     */
    @Test
    void testInsertAndGetById() {
        GameCondition condition = new GameCondition("Excellent", null);
        conditionDao.insert(condition);
        int id = condition.getConditionId();
        assertTrue(id > 0);

        GameCondition retrieved = conditionDao.getById(id);
        assertEquals("Excellent", retrieved.getStatus());
    }

    /**
     * Test update.
     */
    @Test
    void testUpdate() {
        GameCondition condition = conditionDao.getAll().get(0);
        condition.setStatus("Fair");
        conditionDao.update(condition);

        GameCondition updated = conditionDao.getById(condition.getConditionId());
        assertEquals("Fair", updated.getStatus());
    }

    /**
     * Test delete.
     */
    @Test
    void testDelete() {
        GameCondition condition = conditionDao.getAll().get(0);
        conditionDao.delete(condition);

        GameCondition deleted = conditionDao.getById(condition.getConditionId());
        assertNull(deleted);
    }

    /**
     * Test get all.
     */
    @Test
    void testGetAll() {
        List<GameCondition> conditions = conditionDao.getAll();
        assertNotNull(conditions);
    }

    /**
     * Test get by property equal.
     */
    @Test
    void testGetByPropertyEqual() {
        GameCondition condition = new GameCondition("Equal Test", null);
        conditionDao.insert(condition);

        List<GameCondition> results = conditionDao.getByPropertyEqual("status", "Equal Test");
        assertFalse(results.isEmpty());
    }

    /**
     * Test get by property like.
     */
    @Test
    void testGetByPropertyLike() {
        GameCondition condition = new GameCondition("Like Test Condition", null);
        conditionDao.insert(condition);

        List<GameCondition> results = conditionDao.getByPropertyLike("status", "Like Test");
        assertFalse(results.isEmpty());
    }

    @Test
    void testGetByInvalidId() {

        GameCondition result = conditionDao.getById(-1);

        assertNull(result);
    }

    @Test
    void testGetByPropertyEqualEmptyResult() {

        List<GameCondition> results =
                conditionDao.getByPropertyEqual("status", "THIS_DOES_NOT_EXIST");

        assertTrue(results.isEmpty());
    }
}