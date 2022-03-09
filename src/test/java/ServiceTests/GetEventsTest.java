package ServiceTests;
import Models.EventModel;
import Requests.LoginRequest;
import Results.GetEventResult;
import Results.GetEventsResult;
import Services.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetEventsTest extends ServiceTest {
    private String authtoken;

    @BeforeEach
    public void setUp() {
        new Load().load(TEST_LOAD);
        authtoken = (new Login().login( new LoginRequest(TEST_USER.getUsername(), TEST_USER.getPassword()) ).getAuthtoken());
    }

    @Test
    public void positiveSingleTest() {
        EventModel targetEvent = TEST_LOAD.getEvents().get(0);
        GetEventResult res = new GetEvents().getEvent(targetEvent.getEventID(), authtoken);
        assertNotNull(res);
        assertTrue(res.isSuccess());
        assertEquals(res, targetEvent);
    }

    @Test
    public void negativeSingleTest() {
        EventModel targetEvent = TEST_LOAD.getEvents().get(0);
        EventModel otherEvent = new EventModel("btmn", "supes", TEST_USER.getPersonID(), 40, 74, "USA", "Gotham", "Death", 2003);
        assertFalse(new GetEvents().getEvent(otherEvent.getEventID(), authtoken).isSuccess());

    }

    @Test
    public void positiveMultipleTest() {
        GetEventsResult res = new GetEvents().getEvents(authtoken);
        assertNotNull(res);
        assertEquals(TEST_LOAD.getEvents(), res.getData());
    }

    @Test
    public void negativeMultipleTest() {
        GetEventsResult res = new GetEvents().getEvents(authtoken);
        assertNotNull(res);
        assertFalse(res.getData().contains(new EventModel("btmn", "supes", TEST_USER.getPersonID(), 40, 74, "USA", "Gotham", "Death", 2003)));
    }
}