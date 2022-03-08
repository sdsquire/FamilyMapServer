package Services;
import DAOs.*;
import Models.*;
import Resources.*;
import Results.GetEventResult;
import Results.GetEventsResult;
import java.sql.Connection;
import java.util.ArrayList;

public class GetEvents {
    public GetEventsResult getEvents(String authtoken) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            System.out.println("Opening Connection: GetEvents");
            AuthtokenModel thisUser = new AuthtokenDAO(conn).find(authtoken);
            if (thisUser == null)
                throw new DataAccessException("Invalid authorization token");
            String username = thisUser.getUsername();

            GetEventsResult result = new GetEventsResult(new EventDAO(conn).getUserEvents(username));

            db.closeConnection(true);
            System.out.println("Closing connection: GetEvents");
            return result;
        } catch (DataAccessException e){
            db.closeConnection(false);
            System.out.println("Closing connection: GetEvents");
            return new GetEventsResult(e.getMessage());
        }
    }
    public GetEventResult getEvents(String eventID, String authtoken) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            System.out.println("Opening Connection: GetEvent");
            AuthtokenModel thisUser = new AuthtokenDAO(conn).find(authtoken);
            if (thisUser == null)
                throw new DataAccessException("Invalid authorization token");
            String username = thisUser.getUsername();


            GetEventResult result = new GetEventResult(new EventDAO(conn).find(eventID));
            if (!result.getAssociatedUsername().equals(username))
                throw new DataAccessException("User is not authorized to access this person.");

            db.closeConnection(true);

            System.out.println("Closing connection: GetEvent");
            return result;
        } catch (DataAccessException e){
            db.closeConnection(false);
            System.out.println("Closing connection: GetEvent");
            return new GetEventResult(e.getMessage());
        }
    }
}
