package ServiceTests;

import DAOs.PersonDAO;
import DAOs.UserDAO;
import Models.PersonModel;
import Models.UserModel;
import Requests.RegisterRequest;
import Resources.DataAccessException;
import Services.Register;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest extends ServiceTest {

    @Test
    public void positiveTest() {
        try {
            conn = db.openConnection();
            assertNull(new UserDAO(conn).find(TEST_USER.getUsername()));
            db.closeConnection(true);

            assertTrue(new Register().register(TEST_USER_REQUEST).isSuccess());
            conn = db.openConnection();
            UserModel result = new UserDAO(conn).find(TEST_USER.getUsername());
            assertNotNull(result);
            TEST_USER.setPersonID(result.getPersonID());
            assertEquals(TEST_USER, result);

            PersonModel UserPerson = new PersonDAO(conn).find(TEST_USER.getPersonID());
            assertNotNull(UserPerson);
            assertEquals(UserPerson.getFirstName(), TEST_PERSON.getFirstName());

            db.closeConnection(true);
        } catch (DataAccessException | IOException e) {
            db.closeConnection(false);
            fail(e.getMessage());
        }
    }

    @Test
    public void negativeTest() {
        try {
            RegisterRequest req1 = TEST_USER_REQUEST;
            RegisterRequest req2 = TEST_USER_REQUEST;
            req1.setFirstName(null);
            req2.setGender("k");
            assertFalse(new Register().register(req1).isSuccess());
            assertFalse(new Register().register(req2).isSuccess());

            conn = db.openConnection();
            UserDAO uDAO = new UserDAO(conn);
            PersonDAO pDAO = new PersonDAO(conn);
            assertNull(uDAO.find(req1.getUsername()));
            assertNull(uDAO.find(req2.getUsername()));
            assertNull(pDAO.find(req1.getUsername()));
            assertNull(pDAO.find(req2.getUsername()));
            db.closeConnection(true);


        } catch (DataAccessException | IOException e) {
            db.closeConnection(false);
            fail(e.getMessage());
        }
    }
}