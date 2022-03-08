package Services;
import DAOs.*;
import Models.*;
import Resources.*;
import Results.GetPersonResult;
import Results.GetPersonsResult;
import java.sql.Connection;

public class GetPersons {
    public GetPersonsResult getPersons(String authtoken) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            System.out.println("Opening connection: GetPersons");
            AuthtokenModel thisUser = new AuthtokenDAO(conn).find(authtoken);
            if (thisUser == null)
                throw new DataAccessException("Invalid authorization token");
            String username = thisUser.getUsername();

            GetPersonsResult result = new GetPersonsResult(new PersonDAO(conn).getUserPersons(username));

            db.closeConnection(true);
            System.out.println("Closing connection: GetPersons");
            return result;
        } catch (DataAccessException e){
            db.closeConnection(false);
            System.out.println("Closing connection: GetPersons");
            return new GetPersonsResult(e.getMessage());
        }
    }
    public GetPersonResult getPerson(String personID, String authtoken) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            System.out.println("Opening connection: GetPerson");
            AuthtokenModel thisUser = new AuthtokenDAO(conn).find(authtoken);
            if (thisUser == null)
                throw new DataAccessException("Invalid authorization token");
            String username = thisUser.getUsername();

            GetPersonResult result = new GetPersonResult(new PersonDAO(conn).find(personID));
            if (!result.getAssociatedUsername().equals(username))
                throw new DataAccessException("User is not authorized to access this person.");

            db.closeConnection(true);
            System.out.println("Closing connection: GetPerson");
            return result;
        } catch (DataAccessException e){
            db.closeConnection(false);
            System.out.println("Closing connection: GetPerson");
            return new GetPersonResult(e.getMessage());
        }
    }
}
