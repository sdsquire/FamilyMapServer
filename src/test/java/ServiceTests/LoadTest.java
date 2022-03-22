package ServiceTests;
import DAOs.EventDAO;
import DAOs.PersonDAO;
import DAOs.UserDAO;
import Models.*;
import Requests.LoadRequest;
import Resources.DataAccessException;
import Results.LoadResult;
import Services.Load;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoadTest extends ServiceTest {

    @Test
    public void positiveTest () {
        try {
            LoadResult res = new Load().load(TEST_LOAD);
            assertTrue(res.isSuccess());

            conn = db.openConnection();
            UserModel uModel = new UserDAO(conn).find(TEST_LOAD.getUsers().get(0).getUsername());
            assertNotNull(uModel);
            assertEquals(uModel, TEST_USER);

            PersonDAO pDAO = new PersonDAO(conn);
            PersonModel pModel;
            for (PersonModel person : TEST_LOAD.getPersons()){
                pModel = pDAO.find(person.getPersonID());
                assertNotNull(pModel);
                assertEquals(pModel, person);
            }

            EventDAO eDAO = new EventDAO(conn);
            EventModel eModel;
            for (EventModel event : TEST_LOAD.getEvents()){
                eModel = eDAO.find(event.getEventID());
                assertNotNull(eModel);
                assertEquals(eModel, event);
            }

        } catch (DataAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void negativeTest() {
        try {
            LoadRequest req = TEST_LOAD;
            req.getPersons().get(2).setFirstName(null);
            LoadResult res = new Load().load(req);
            assertFalse(res.isSuccess());

            conn = db.openConnection();
            UserModel uModel = new UserDAO(conn).find(TEST_LOAD.getUsers().get(0).getUsername());
            assertNull(uModel);

            PersonDAO pDAO = new PersonDAO(conn);
            for (PersonModel person : TEST_LOAD.getPersons())
                assertNull(pDAO.find(person.getPersonID()));

            EventDAO eDAO = new EventDAO(conn);
            for (EventModel event : TEST_LOAD.getEvents())
                assertNull(eDAO.find(event.getEventID()));

        } catch (DataAccessException e) {
            fail(e.getMessage());
        }

    }
}