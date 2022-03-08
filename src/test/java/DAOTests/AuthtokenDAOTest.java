package DAOTests;
import Resources.*;
import DAOs.AuthtokenDAO;
import Models.AuthtokenModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

public class AuthtokenDAOTest {
    private Database db;
    private AuthtokenModel bestAuthtoken;
    private AuthtokenDAO aDao;

    @BeforeEach
    public void setUp () throws DataAccessException {
        db = new Database();
        bestAuthtoken = new AuthtokenModel("144300", "sdsquire");
        Connection conn = db.getConnection();
        aDao = new AuthtokenDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.clearTables();
        db.closeConnection(true);
    }

    @Test
    public void insertFindTest() throws DataAccessException {
        aDao.insert(bestAuthtoken);
        AuthtokenModel findTest = aDao.find(bestAuthtoken.getAuthtoken());

        assertNotNull(findTest);
        assertEquals(bestAuthtoken, findTest);
    }

    @Test
    public void negativeFindTest() throws DataAccessException {
        assertNull(aDao.find(bestAuthtoken.getAuthtoken()));
    }

    @Test
    public void doubleInsertTest() throws DataAccessException {
        aDao.insert(bestAuthtoken);
        assertThrows(DataAccessException.class, () -> aDao.insert(bestAuthtoken));
    }

    @Test
    public void clearTest() throws DataAccessException {
        aDao.insert(bestAuthtoken);
        AuthtokenModel findTest = aDao.find(bestAuthtoken.getAuthtoken());
        assertNotNull(findTest);
        assertEquals(findTest, bestAuthtoken);

        aDao.Clear();
        assertNull(aDao.find(bestAuthtoken.getAuthtoken()));
    }
}
