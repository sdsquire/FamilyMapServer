package DAOTests;

import DAOs.DataAccessException;
import DAOs.Database;
import DAOs.UserDAO;
import Models.UserModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private Database db;
    private UserModel bestUser;
    private UserDAO uDao;

    @BeforeEach
    public void setUp () throws DataAccessException {
        db = new Database();
        bestUser = new UserModel("sdsquire", "myPassword", "sdsquire@byu.edu", "Sam", "Squires", "m", "3505");
        Connection conn = db.getConnection();
        uDao = new UserDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.clearTables();
        db.closeConnection(true);
    }

    @Test
    public void insertFindTest() throws DataAccessException {
        uDao.insert(bestUser);
        UserModel findTest = uDao.find(bestUser.getUsername());

        assertNotNull(findTest);
        assertEquals(bestUser, findTest);
    }

    @Test
    public void negativeFindTest() throws DataAccessException {
        assertNull(uDao.find(bestUser.getUsername()));
    }

    @Test
    public void doubleInsertTest() throws DataAccessException {
        uDao.insert(bestUser);
        assertThrows(DataAccessException.class, () -> uDao.insert(bestUser));
    }

    @Test
    public void clearTest() throws DataAccessException {
        uDao.insert(bestUser);
        UserModel findTest = uDao.find(bestUser.getUsername());
        assertNotNull(findTest);
        assertEquals(findTest, bestUser);

        uDao.Clear();
        assertNull(uDao.find(bestUser.getUsername()));
    }
}
