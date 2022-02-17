package DAOTests;

import DAOs.DataAccessException;
import DAOs.Database;
import DAOs.EventDAO;
import Models.EventModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

public class EventDAOTest {
    private Database db;
    private EventModel bestEvent;
    private EventDAO eDao;

    @BeforeEach
    public void setUp () throws DataAccessException {
        db = new Database();
        bestEvent = new EventModel("1357", "sdsquire", "3505", 144, 300, "USA", "Provo", "skiing", 2022);
        Connection conn = db.getConnection();
        eDao = new EventDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.clearTables();
        db.closeConnection(true);
    }

    @Test
    public void insertFindTest() throws DataAccessException {
        eDao.insert(bestEvent);
        EventModel findTest = eDao.find(bestEvent.getEventID());

        assertNotNull(findTest);
        assertEquals(bestEvent, findTest);
    }

    @Test
    public void negativeFindTest() throws DataAccessException {
        assertNull(eDao.find(bestEvent.getEventID()));
    }

    @Test
    public void doubleInsertTest() throws DataAccessException {
        eDao.insert(bestEvent);
        assertThrows(DataAccessException.class, () -> eDao.insert(bestEvent));
    }

    @Test
    public void clearTest() throws DataAccessException {
        eDao.insert(bestEvent);
        EventModel findTest = eDao.find(bestEvent.getEventID());
        assertNotNull(findTest);
        assertEquals(findTest, bestEvent);

        eDao.Clear();
        assertNull(eDao.find(bestEvent.getEventID()));
    }
}
