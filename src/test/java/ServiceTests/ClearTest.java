package ServiceTests;
import DAOs.*;
import Models.*;
import Requests.RegisterRequest;
import Resources.*;
import Results.RegisterResult;
import Services.Clear;
import Services.Register;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

public class ClearTest {
    private Database db;
    private final RegisterRequest TEST_USER = new RegisterRequest("sdsquires", "CS240", "sdsquire@byu.edu", "Sam", "Squires", "m");
    private Connection conn;
    private DAO[] DAOs;

    @Test
    public void ClearTest1() {
        System.out.println("Clear Test 1");
        Database db = new Database();
        try {
            conn = db.openConnection();
            RegisterResult result = new Register().register(TEST_USER);
            db.closeConnection(true);
            assertTrue(new Clear().clear().isSuccess());
            conn = db.openConnection();
            assertNull(new UserDAO(conn).find(TEST_USER.getUsername()));
            assertNull(new AuthtokenDAO(conn).find(result.getAuthtoken()));
            assertNull(new PersonDAO(conn).find(result.getPersonID()));
            db.closeConnection(true);
        } catch (IOException | DataAccessException e) {
            db.closeConnection(false);
            fail(e.getMessage());
        }
    }

    @Test
    public void ClearTest2() {
        System.out.println("Clear Test 2");
        Database db = new Database();
        try {
            conn = db.openConnection();

            new AuthtokenDAO(conn).insert(new AuthtokenModel("1234567890", "qwerty"));
            new EventDAO(conn).insert(new EventModel("1234567890-", "qwerty", "1234567890", 123, 1234, "usa", "smallville", "graduation", 1901));
            new PersonDAO(conn).insert(new PersonModel("1234567890", "qwerty", "clark", "kent", "m", null, null, null));
            new UserDAO(conn).insert(new UserModel("qwerty", "0987654321", "clark@smallvile.com", "clark", "kent", "m", "1234567890"));
            db.closeConnection(true);

            conn = db.openConnection();
            assertTrue(new Clear().clear().isSuccess());
            assertNull(new AuthtokenDAO(conn).find("1234567890"));
            assertNull(new EventDAO(conn).find("1234567890-"));
            assertNull(new PersonDAO(conn).find("1234567890"));
            assertNull(new UserDAO(conn).find("qwerty"));
            db.closeConnection(true);
        } catch (DataAccessException e) {
            db.closeConnection(false);
            fail(e.getMessage());
        }
    }

}