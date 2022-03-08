package ServiceTests;
import Models.PersonModel;
import Requests.LoginRequest;
import Results.GetPersonResult;
import Results.GetPersonsResult;
import Services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetPersonsTest extends ServiceTest {
    private String authtoken;

    @BeforeEach
    public void setUp() {
        new Load().load(TEST_LOAD);
        authtoken = (new Login().login( new LoginRequest(TEST_USER.getUsername(), TEST_USER.getPassword()) ).getAuthtoken());
    }

    @Test
    public void positiveSingleTest() {
        PersonModel targetPerson = TEST_LOAD.getPersons().get(0);
        GetPersonResult res = new GetPersons().getPerson(targetPerson.getPersonID(), authtoken);
        assertNotNull(res);
        assertEquals(res, targetPerson);
    }

    @Test
    public void negativeSingleTest() {
        PersonModel targetPerson = TEST_LOAD.getPersons().get(0);
        GetPersonResult res = new GetPersons().getPerson(targetPerson.getPersonID(), authtoken);
        assertNotEquals(res, TEST_LOAD.getPersons().get(1));
    }

    @Test
    public void positiveMultipleTest() {
        GetPersonsResult res = new GetPersons().getPersons(authtoken);
        assertNotNull(res);
        assertEquals(TEST_LOAD.getPersons(), res.getData());
    }

    @Test
    public void negativeMultipleTest() {
        GetPersonsResult res = new GetPersons().getPersons(authtoken);
        assertNotNull(res);
        assertFalse(res.getData().contains(new PersonModel("041940", "supes", "Lex", "Luthor", "m", null, null, null)));
    }
}