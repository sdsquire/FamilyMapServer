package ServiceTests;
import DAOs.AuthtokenDAO;
import Requests.LoginRequest;
import Resources.DataAccessException;
import Results.LoginResult;
import Services.Login;
import Services.Register;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends ServiceTest{
    @Test
    public void positiveTest() {
        try {
            new Register().register(TEST_USER_REQUEST);
            LoginRequest req = new LoginRequest(TEST_USER_REQUEST.getUsername(), TEST_USER_REQUEST.getPassword());
            LoginResult res = new Login().login(req);
            assertNotNull(res);
            assertTrue(res.isSuccess());

            conn = db.openConnection();
            assertEquals(new AuthtokenDAO(conn).find(res.getAuthtoken()).getUsername(), req.getUsername());
            db.closeConnection(true);
        } catch (DataAccessException | IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void negativeTest() {
        try {
            new Register().register(TEST_USER_REQUEST);
            LoginRequest req = new LoginRequest(TEST_USER_REQUEST.getUsername(), TEST_USER_REQUEST.getPassword());
            LoginResult res = new Login().login(req);
            assertNotNull(res);
            assertTrue(res.isSuccess());

            conn = db.openConnection();
            assertEquals(new AuthtokenDAO(conn).find(res.getAuthtoken()).getUsername(), req.getUsername());
            db.closeConnection(true);
        } catch (DataAccessException | IOException e) {
            fail(e.getMessage());
        }
    }
}