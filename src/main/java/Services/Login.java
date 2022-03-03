package Services;

import DAOs.*;
import Models.*;
import Resources.*;
import Requests.LoginRequest;
import Results.LoginResult;

import java.sql.Connection;
import java.util.UUID;

/** Logs a returning user into the server */
public class Login {
    /**
    * Generates an authorization token and returns it to the user.
    * @return A result containing the authtoken, personID, and personID.
    */
    public LoginResult login(LoginRequest req) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            // CHECK REQUEST VALIDITY
            UserModel user = new UserDAO(conn).find(req.getUsername());
            if (user == null)
                throw new DataAccessException("No user exists with username \"" + req.getUsername() + "\"");
            if (!user.getPassword().equals(req.getPassword()))
                throw new InvalidRequestException("Incorrect password");

            // INSERT NEW AUTHTOKEN-USER PAIR INTO DATABASE //
            String personID = user.getPersonID();
            String authtoken = UUID.randomUUID().toString();
            new AuthtokenDAO(conn).insert(new AuthtokenModel(authtoken, req.getUsername()));

            db.closeConnection(true);
            return new Results.LoginResult(authtoken, req.getUsername(), personID);
        } catch (DataAccessException | InvalidRequestException e) {
            return new Results.LoginResult(e.getMessage());
        }
    }

}
