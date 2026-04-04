package com.vintagearcade.persistence;

import com.vintagearcade.entity.GameCondition;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameConditionDaoTest {

    private GenericDao<GameCondition> conditionDao;

    @BeforeAll
    void setup() {
        conditionDao = new GenericDao<>(GameCondition.class);
    }

    @Test
    void testInsertAndGetById() {
        GameCondition condition = new GameCondition("Excellent", null);
        conditionDao.insert(condition);
        int id = condition.getConditionId();
        assertTrue(id > 0);

        GameCondition retrieved = conditionDao.getById(id);
        assertEquals("Excellent", retrieved.getStatus());
    }

    @Test
    void testUpdate() {
        GameCondition condition = conditionDao.getAll().get(0);
        condition.setStatus("Fair");
        conditionDao.update(condition);

        GameCondition updated = conditionDao.getById(condition.getConditionId());
        assertEquals("Fair", updated.getStatus());
    }

    @Test
    void testDelete() {
        GameCondition condition = conditionDao.getAll().get(0);
        conditionDao.delete(condition);

        GameCondition deleted = conditionDao.getById(condition.getConditionId());
        assertNull(deleted);
    }

    @Test
    void testGetAll() {
        List<GameCondition> conditions = conditionDao.getAll();
        assertNotNull(conditions);
    }

    @Test
    void testGetByPropertyEqual() {
        GameCondition condition = new GameCondition("Equal Test", null);
        conditionDao.insert(condition);

        List<GameCondition> results = conditionDao.getByPropertyEqual("status", "Equal Test");
        assertFalse(results.isEmpty());
    }

    @Test
    void testGetByPropertyLike() {
        GameCondition condition = new GameCondition("Like Test Condition", null);
        conditionDao.insert(condition);

        List<GameCondition> results = conditionDao.getByPropertyLike("status", "Like Test");
        assertFalse(results.isEmpty());
    }
}