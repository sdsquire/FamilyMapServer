package ServiceTests;
import DAOs.*;
import Resources.*;
import Services.Fill;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class FillTest extends ServiceTest{

    @Test
    public void positiveTest() {
        try {
            conn = db.openConnection();
            new UserDAO(conn).insert(TEST_USER);
            new PersonDAO(conn).insert(TEST_PERSON);
            assertEquals(new EventDAO(conn).getUserEvents(TEST_USER.getUsername()).size(), 0);
            assertEquals(new PersonDAO(conn).getUserPersons(TEST_USER.getUsername()).size(), 1);

            db.closeConnection(true);
            assertTrue(new Fill(TEST_USER.getUsername()).fill(4).isSuccess());
            conn = db.openConnection();

            assertEquals(91, new EventDAO(conn).getUserEvents(TEST_USER.getUsername()).size());
            assertEquals(31, new PersonDAO(conn).getUserPersons(TEST_USER.getUsername()).size());
        } catch (DataAccessException | IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void negativeTest() {
        try {
            assertFalse(new Fill(TEST_USER.getUsername()).fill(4).isSuccess());
            conn = db.openConnection();
            new UserDAO(conn).insert(TEST_USER);
            db.closeConnection(true);

            new Fill(TEST_USER.getUsername()).fill(4);
            fail("Fail should throw a NullPointerException in the absence of a Person or User");
        } catch (DataAccessException | IOException e) {
            fail(e.getMessage());
        } catch (NullPointerException e) {
                //Test case Passes
        }
    }
}