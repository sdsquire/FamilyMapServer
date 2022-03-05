package Services;
import DAOs.*;
import Models.*;
import Resources.*;
import Results.GetPersonResult;
import Results.GetPersonsResult;
import java.sql.Connection;
import java.util.ArrayList;

public class GetPersons {
    public GetPersonsResult getPersons(String authtoken) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            AuthtokenModel thisUser = new AuthtokenDAO(conn).find(authtoken);
            if (thisUser == null)
                return new GetPersonsResult("Invalid authorization token");
            String username = thisUser.getUsername();

            GetPersonsResult result = new GetPersonsResult(new PersonDAO(conn).getUserPersons(username));

            db.closeConnection(true);
            return result;
        } catch (DataAccessException e){
            db.closeConnection(false);
            return new GetPersonsResult(e.getMessage());
        }
    }
    public GetPersonResult getPersons(String personID, String authtoken) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            AuthtokenModel thisUser = new AuthtokenDAO(conn).find(authtoken);
            if (thisUser == null)
                return new GetPersonResult("Invalid authorization token");
            String username = thisUser.getUsername();

            GetPersonResult result = new GetPersonResult(new PersonDAO(conn).find(personID));

            db.closeConnection(true);
            return result;
        } catch (DataAccessException e){
            db.closeConnection(false);
            return new GetPersonResult(e.getMessage());
        }
    }
}