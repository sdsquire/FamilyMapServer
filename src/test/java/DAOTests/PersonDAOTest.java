package DAOTests;

import DAOs.DataAccessException;
import DAOs.Database;
import DAOs.PersonDAO;
import Models.PersonModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {
    private Database db;
    private PersonModel bestPerson;
    private PersonDAO pDao;

    @BeforeEach
    public void setUp () throws DataAccessException {
        db = new Database();
        bestPerson = new PersonModel("3505", "sdsquire", "Sam", "Squires", "m", "1340", "2298", "144300");
        Connection conn = db.getConnection();
        pDao = new PersonDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.clearTables();
        db.closeConnection(true);
    }

    @Test
    public void insertFindTest() throws DataAccessException {
        pDao.insert(bestPerson);
        PersonModel findTest = pDao.find(bestPerson.getPersonID());

        assertNotNull(findTest);
        assertEquals(bestPerson, findTest);
    }

    @Test
    public void negativeFindTest() throws DataAccessException {
        assertNull(pDao.find(bestPerson.getPersonID()));
    }

    @Test
    public void doubleInsertTest() throws DataAccessException {
        pDao.insert(bestPerson);
        assertThrows(DataAccessException.class, () -> pDao.insert(bestPerson));
    }

    @Test
    public void clearTest() throws DataAccessException {
        pDao.insert(bestPerson);
        PersonModel findTest = pDao.find(bestPerson.getPersonID());
        assertNotNull(findTest);
        assertEquals(findTest, bestPerson);

        pDao.Clear();
        assertNull(pDao.find(bestPerson.getPersonID()));
    }
}