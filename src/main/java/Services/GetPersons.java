package Services;
import DAOs.*;
import Models.*;
import Resources.*;
import Results.GetPersonsResult;
import java.sql.Connection;
import java.util.ArrayList;

public class GetPersons {
    public GetPersonsResult getPersons(String personID, String authtoken) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            AuthtokenModel thisUser = new AuthtokenDAO(conn).find(authtoken);
            if (thisUser == null)
                return new GetPersonsResult("Invalid authorization token");
            String username = thisUser.getUsername();

            ArrayList<PersonModel> result = new ArrayList<>();
            if (personID == null) result.addAll(new PersonDAO(conn).getUserPersons(username));
            else result.add(new PersonDAO(conn).find(personID));

            db.closeConnection(true);
            return new GetPersonsResult(result);
        } catch (DataAccessException e){
            db.closeConnection(false);
            return new GetPersonsResult(e.getMessage());
        }
    }
}