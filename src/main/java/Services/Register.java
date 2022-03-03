package Services;
import DAOs.*;
import Models.*;
import Resources.*;
import Requests.RegisterRequest;
import Results.RegisterResult;
import java.sql.Connection;
import java.util.UUID;

/** Registers a new user. */
public class Register {
    /**
    * Registers a new user.
    * @return A RegisterResult containing an authtoken, username, and personID.
    */
    public RegisterResult register(RegisterRequest req) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            // SET UP MODELS WITH UNIQUE IDENTIFIERS //
            String personID = UUID.randomUUID().toString();
            String authtoken = UUID.randomUUID().toString();
            PersonModel pModel = new PersonModel(personID, req.getUsername(), req.getFirstName(), req.getLastName(),
                                                req.getGender(), null, null, null);
            UserModel uModel = new UserModel(req.getUsername(), req.getPassword(), req.getEmail(), req.getFirstName(), req.getLastName(), req.getGender(), personID);
            AuthtokenModel aModel = new AuthtokenModel(authtoken, req.getUsername());

            // INSERT INTO DAOs //
            new PersonDAO(conn).insert(pModel);
            new UserDAO(conn).insert(uModel);
            new AuthtokenDAO(conn).insert(aModel);

            db.closeConnection(true);
            return new RegisterResult(authtoken, req.getUsername(), personID);
        } catch (DataAccessException e) {
            try {
                db.closeConnection(false);
            } catch (DataAccessException ex) {}
            return new RegisterResult(e.getMessage());
        }
    }
}
