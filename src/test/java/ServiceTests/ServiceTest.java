package ServiceTests;
import DAOs.*;
import Models.*;
import Requests.LoadRequest;
import Requests.RegisterRequest;
import Resources.DataAccessException;
import Resources.Database;
import Services.Clear;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

public class ServiceTest{
    protected final Database db = new Database();
    Connection conn;
    protected UserModel TEST_USER = new UserModel("supes", "BatmanSmells1938", "superman@JusticeLeague.com", "Clark", "Kent", "m", "18041938");
    protected PersonModel TEST_PERSON = new PersonModel(TEST_USER.getPersonID(), "supes", "Clark", "Kent", "m", "jnthnknt1938", "mthknt1938", null);
    protected PersonModel TEST_MOTHER = new PersonModel("mthknt1938", "supes", "Martha", "Kent", "f", null, null, "jnthnknt1938");
    protected PersonModel TEST_FATHER = new PersonModel("jnthnknt1938", "supes", "Jonathan", "Kent", "m", null, null,"mthknt1938");
    protected final RegisterRequest TEST_USER_REQUEST = new RegisterRequest(TEST_USER.getUsername(), TEST_USER.getPassword(), TEST_USER.getEmail(), TEST_USER.getFirstName(), TEST_USER.getLastName(), TEST_USER.getGender());
    protected final EventModel TEST_BIRTH = new EventModel("Krptn", "supes", TEST_USER.getPersonID(), 38, 98,"USA", "Smallville", "birth", 1938);
    protected final EventModel TEST_DEATH = new EventModel("Dmsdy", "supes", TEST_USER.getPersonID(),39, 100, "USA", "Metropolis", "death", 1993);
    public final LoadRequest TEST_LOAD = new LoadRequest(
            new ArrayList<UserModel>(Arrays.asList(TEST_USER)),
            new ArrayList<PersonModel>(Arrays.asList(TEST_PERSON, TEST_MOTHER, TEST_FATHER)),
            new ArrayList<EventModel>(Arrays.asList(TEST_BIRTH, TEST_DEATH)));

    @BeforeEach
    public void setUp() { new Clear().clear(); }
    @AfterEach
    public void tearDown() {
        db.closeConnection(false);
        new Clear().clear();
    }

    protected boolean dbIsEmpty() {
        try {
            conn = db.openConnection();
            DAO[] DAOs = {new AuthtokenDAO(conn), new EventDAO(conn), new PersonDAO(conn), new UserDAO(conn)};
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}